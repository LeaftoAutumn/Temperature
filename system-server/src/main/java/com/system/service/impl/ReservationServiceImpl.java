package com.system.service.impl;

import com.system.dto.*;
import com.system.entity.Course;
import com.system.entity.Reservation;
import com.system.mapper.*;
import com.system.service.ReservationService;
import com.system.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private ReservationMapper reservationMapper;

    @Autowired
    private CoachStudentMatchMapper coachStudentMatchMapper;

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private CoachMapper coachMapper;

    @Override
    public List<AvailableTimeSlotVO> getAvailableTimeSlots(AvailableTimeSlotQueryDTO queryDTO) {
        log.info("查询可用时段: coachId={}, date={}", queryDTO.getCoachId(), queryDTO.getDate());
        
        List<LocalDateTime> timeSlots = courseMapper.getAvailableTimeSlots(
            queryDTO.getCoachId(),
            queryDTO.getDate().toString(),
            queryDTO.getDuration() != null ? queryDTO.getDuration() : 60
        );
        
        return timeSlots.stream().map(time -> {
            LocalDateTime endTime = time.plusMinutes(queryDTO.getDuration() != null ? queryDTO.getDuration() : 60);
            return AvailableTimeSlotVO.builder()
                    .startTime(time)
                    .endTime(endTime)
                    .availableTables(Arrays.asList(1, 2, 3, 4, 5)) // 示例数据
                    .build();
        }).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public ReservationDetailVO createReservation(CreateReservationRequestDTO createRequestDTO) {
        log.info("创建课程预约: studentId={}, coachId={}", createRequestDTO.getStudentId(), createRequestDTO.getCoachId());
        
        UUID studentId = createRequestDTO.getStudentId();
        UUID coachId = createRequestDTO.getCoachId();
        
        // 验证双选关系
        if (!reservationMapper.existsCoachStudentMatch(studentId, coachId)) {
            throw new RuntimeException("学员与教练无双选关系");
        }
        
        // 验证时段冲突
        if (courseMapper.existsConflict(coachId, createRequestDTO.getStartTime(), 
                                      createRequestDTO.getEndTime(), null)) {
            throw new RuntimeException("该时段已被占用");
        }
        
        // 获取教练费率
        BigDecimal hourlyRate = coachMapper.getHourlyRate(coachId);
        long durationMinutes = java.time.Duration.between(
            createRequestDTO.getStartTime(), createRequestDTO.getEndTime()).toMinutes();
        BigDecimal price = hourlyRate.multiply(BigDecimal.valueOf(durationMinutes / 60.0));
        
        // 验证学员余额
        BigDecimal studentBalance = studentMapper.getBalance(studentId);
        if (studentBalance.compareTo(price) < 0) {
            throw new RuntimeException("学员余额不足");
        }
        
        // 创建课程记录
        Course course = Course.builder()
                .id(UUID.randomUUID())
                .coachId(coachId)
                .studentId(studentId)
                .startTime(createRequestDTO.getStartTime())
                .endTime(createRequestDTO.getEndTime())
                .price(price)
                .status("pending")
                .tableNumber(createRequestDTO.getTableNumber())
                .createTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .deleted(false)
                .build();
        
        courseMapper.insertCourse(course);
        
        // 创建预约记录
        Reservation reservation = Reservation.builder()
                .id(UUID.randomUUID())
                .courseId(course.getId())
                .studentId(studentId)
                .coachId(coachId)
                .action("reserve")
                .status("pending")
                .createTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .deleted(false)
                .build();
        
        reservationMapper.insertReservation(reservation);
        
        return getReservationDetail(course.getId().toString());
    }

    @Override
    public ReservationPageVO listReservations(ReservationQueryDTO queryDTO, String currentUserId) {
        log.info("获取预约列表: userId={}", currentUserId);
        
        UUID userId = UUID.fromString(currentUserId);
        
        // 设置分页参数
        Integer page = queryDTO.getPage() != null ? queryDTO.getPage() : 1;
        Integer limit = queryDTO.getLimit() != null ? queryDTO.getLimit() : 20;
        long offset = (page - 1) * limit;
        
        // 查询预约列表
        List<ReservationListItemVO> reservations = courseMapper.selectReservationsByUserId(
            userId, queryDTO, offset, limit);
        
        // 查询总数
        long total = courseMapper.countReservationsByUserId(userId, queryDTO);
        
        // 计算总页数
        int pages = (int) Math.ceil((double) total / limit);
        
        PaginationVO pagination = PaginationVO.builder()
                .page(page)
                .limit(limit)
                .total(total)
                .pages(pages)
                .build();
        
        return ReservationPageVO.builder()
                .reservations(reservations)
                .pagination(pagination)
                .build();
    }

    @Transactional
    @Override
    public ReservationDetailVO updateReservationStatus(String reservationId, UpdateReservationStatusDTO updateDTO, String currentUserId) {
        log.info("更新预约状态: reservationId={}, status={}", reservationId, updateDTO.getStatus());
        
        UUID reservationUUID = UUID.fromString(reservationId);
        UUID currentUserUUID = UUID.fromString(currentUserId);
        
        Reservation reservation = reservationMapper.selectById(reservationUUID);
        if (reservation == null) {
            throw new RuntimeException("预约不存在");
        }
        
        // 验证权限（只有教练可以处理自己的预约）
        if (!reservation.getCoachId().equals(currentUserUUID)) {
            throw new RuntimeException("无权处理此预约");
        }
        
        if (!"pending".equals(reservation.getStatus())) {
            throw new RuntimeException("预约已被处理过");
        }
        
        if ("confirmed".equals(updateDTO.getStatus())) {
            // 确认预约：扣除学员费用
            Course course = courseMapper.selectById(reservation.getCourseId());
            BigDecimal studentBalance = studentMapper.getBalance(reservation.getStudentId());
            
            if (studentBalance.compareTo(course.getPrice()) < 0) {
                throw new RuntimeException("学员余额不足");
            }
            
            // 扣除费用
            studentMapper.updateBalance(reservation.getStudentId(), 
                                      studentBalance.subtract(course.getPrice()));
        }
        
        // 更新预约状态
        reservationMapper.updateReservationStatus(reservationUUID, updateDTO.getStatus());
        
        // 更新课程状态
        courseMapper.updateCourseStatus(reservation.getCourseId(), updateDTO.getStatus());
        
        return getReservationDetail(reservation.getCourseId().toString());
    }

    @Override
    public List<TimetableItemVO> getUserTimetable(String userId, TimetableQueryDTO queryDTO) {
        log.info("获取用户课表: userId={}", userId);
        
        UUID userUUID = UUID.fromString(userId);
        
        // 计算结束日期
        LocalDateTime startDate = queryDTO.getStartDate().atStartOfDay();
        LocalDateTime endDate = queryDTO.getEndDate() != null ? 
            queryDTO.getEndDate().atTime(23, 59, 59) : 
            startDate.plusDays(7);
        
        return courseMapper.selectTimetableByUserId(userUUID, startDate, endDate);
    }

    @Override
    public CancellationRemainingVO getRemainingCancellations(String currentUserId) {
        log.info("获取剩余取消次数: userId={}", currentUserId);
        
        UUID studentId = UUID.fromString(currentUserId);
        String currentMonth = YearMonth.now().toString();
        
        Integer usedCount = studentMapper.getMonthlyCancelCount(studentId, currentMonth);
        int remaining = 5 - (usedCount != null ? usedCount : 0); // 假设每月最多取消5次
        
        return CancellationRemainingVO.builder()
                .remaining(Math.max(remaining, 0))
                .used(usedCount != null ? usedCount : 0)
                .build();
    }

    @Transactional
    @Override
    public CancellationRequestVO createCancellation(CreateCancellationRequestDTO createDTO, String currentUserId) {
        log.info("创建取消申请: reservationId={}", createDTO.getReservationId());
        
        UUID currentUserUUID = UUID.fromString(currentUserId);
        UUID reservationUUID = createDTO.getReservationId();
        
        Reservation reservation = reservationMapper.selectById(reservationUUID);
        if (reservation == null) {
            throw new RuntimeException("预约不存在");
        }
        
        // 验证权限
        if (!reservation.getStudentId().equals(currentUserUUID) && 
            !reservation.getCoachId().equals(currentUserUUID)) {
            throw new RuntimeException("无权取消此预约");
        }
        
        Course course = courseMapper.selectById(reservation.getCourseId());
        
        // 验证是否提前24小时
        if (course.getStartTime().isBefore(LocalDateTime.now().plusHours(24))) {
            throw new RuntimeException("必须提前24小时取消");
        }
        
        // 验证学员取消次数（如果是学员取消）
        if (reservation.getStudentId().equals(currentUserUUID)) {
            CancellationRemainingVO remaining = getRemainingCancellations(currentUserId);
            if (remaining.getRemaining() <= 0) {
                throw new RuntimeException("本月取消次数已用完");
            }
        }
        
        // 更新预约状态为取消
        reservationMapper.updateReservationStatus(reservationUUID, "cancelled");
        courseMapper.updateCourseStatus(reservation.getCourseId(), "cancelled");
        
        // 如果是学员取消，更新取消次数
        if (reservation.getStudentId().equals(currentUserUUID)) {
            String currentMonth = YearMonth.now().toString();
            Integer usedCount = studentMapper.getMonthlyCancelCount(currentUserUUID, currentMonth);
            int newCount = (usedCount != null ? usedCount : 0) + 1;
            studentMapper.updateCancelCount(currentUserUUID, newCount, Integer.parseInt(currentMonth.replace("-", "")));
        }
        
        // 退款处理（如果已支付）
        if ("confirmed".equals(reservation.getStatus())) {
            BigDecimal studentBalance = studentMapper.getBalance(reservation.getStudentId());
            studentMapper.updateBalance(reservation.getStudentId(), 
                                      studentBalance.add(course.getPrice()));
        }
        
        // 创建取消申请记录（这里简化处理，实际可能需要单独的取消申请表）
        return CancellationRequestVO.builder()
                .id(UUID.randomUUID())
                .reservationId(createDTO.getReservationId())
                .initiatedBy(reservation.getStudentId().equals(currentUserUUID) ? "student" : "coach")
                .reason(createDTO.getReason())
                .status("confirmed")
                .createTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .build();
    }

    @Override
    public CancellationRequestVO confirmCancellation(String cancellationId, ConfirmCancellationDTO confirmDTO, String currentUserId) {
        // 简化实现：直接返回取消申请信息
        // 实际实现需要验证取消申请的存在性和权限
        log.info("确认取消申请: cancellationId={}, confirm={}", cancellationId, confirmDTO.getConfirm());
        
        return CancellationRequestVO.builder()
                .id(UUID.fromString(cancellationId))
                .reservationId(UUID.fromString("mock-reservation-id"))
                .initiatedBy("student")
                .reason("临时有事")
                .status(confirmDTO.getConfirm() ? "confirmed" : "rejected")
                .createTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .build();
    }
    
    private ReservationDetailVO getReservationDetail(String courseId) {
        Course course = courseMapper.selectById(UUID.fromString(courseId));
        if (course == null) {
            throw new RuntimeException("课程不存在");
        }
        
        // 这里需要查询相关的用户信息来构建完整的ReservationListItemVO
        ReservationListItemVO reservationItem = ReservationListItemVO.builder()
                .id(course.getId())
                .studentId(course.getStudentId())
                .studentName("学员姓名") // 需要从数据库查询
                .coachId(course.getCoachId())
                .coachName("教练姓名") // 需要从数据库查询
                .startTime(course.getStartTime())
                .endTime(course.getEndTime())
                .duration((int) java.time.Duration.between(course.getStartTime(), course.getEndTime()).toMinutes())
                .price(course.getPrice())
                .status(course.getStatus())
                .tableNumber(course.getTableNumber())
                .createTime(course.getCreateTime())
                .build();
        
        return ReservationDetailVO.builder()
                .reservation(reservationItem)
                .courseId(course.getId())
                .canCancel(canCancelReservation(course))
                .build();
    }
    
    private boolean canCancelReservation(Course course) {
        // 验证是否可以取消：提前24小时且状态为pending或confirmed
        return course.getStartTime().isAfter(LocalDateTime.now().plusHours(24)) &&
               ("pending".equals(course.getStatus()) || "confirmed".equals(course.getStatus()));
    }
}