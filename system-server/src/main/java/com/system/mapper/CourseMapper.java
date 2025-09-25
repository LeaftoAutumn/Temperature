package com.system.mapper;

import com.system.dto.ReservationQueryDTO;
import com.system.entity.Course;
import com.system.vo.ReservationListItemVO;
import com.system.vo.TimetableItemVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Mapper
public interface CourseMapper {

    int insertCourse(Course course);
    
    Course selectById(@Param("id") UUID id);
    
    List<Course> selectByStudentId(@Param("studentId") UUID studentId);
    
    List<Course> selectByCoachId(@Param("coachId") UUID coachId);
    
    List<ReservationListItemVO> selectReservationsByUserId(@Param("userId") UUID userId, 
                                                         @Param("queryDTO") ReservationQueryDTO queryDTO,
                                                         @Param("offset") long offset, 
                                                         @Param("limit") int limit);
    
    long countReservationsByUserId(@Param("userId") UUID userId, 
                                 @Param("queryDTO") ReservationQueryDTO queryDTO);
    
    List<TimetableItemVO> selectTimetableByUserId(@Param("userId") UUID userId, 
                                                @Param("startDate") LocalDateTime startDate, 
                                                @Param("endDate") LocalDateTime endDate);
    
    int updateCourseStatus(@Param("id") UUID id, @Param("status") String status);
    
    boolean existsConflict(@Param("coachId") UUID coachId, 
                         @Param("startTime") LocalDateTime startTime, 
                         @Param("endTime") LocalDateTime endTime,
                         @Param("excludeCourseId") UUID excludeCourseId);
    
    List<LocalDateTime> getAvailableTimeSlots(@Param("coachId") UUID coachId, 
                                            @Param("date") String date, 
                                            @Param("duration") int duration);
}