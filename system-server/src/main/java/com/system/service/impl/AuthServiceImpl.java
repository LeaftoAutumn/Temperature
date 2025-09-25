// AuthServiceImpl.java
package com.system.service.impl;

import com.system.dto.LoginRequestDTO;
import com.system.dto.RegisterRequestDTO;
import com.system.entity.Coach;
import com.system.entity.Student;
import com.system.entity.User;
import com.system.mapper.CoachMapper;
import com.system.mapper.StudentMapper;
import com.system.mapper.UserMapper;
import com.system.service.AuthService;
import com.system.service.JwtService;
import com.system.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserMapper userMapper;
    
    @Autowired
    private StudentMapper studentMapper;
    
    @Autowired
    private CoachMapper coachMapper;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private JwtService jwtService;

    @Override
    public LoginResponseVO login(LoginRequestDTO loginRequestDTO) {
        log.info("用户登录: {}", loginRequestDTO.getUsername());
        
        User user = userMapper.selectByUsername(loginRequestDTO.getUsername());
        if (user == null || !passwordEncoder.matches(loginRequestDTO.getPassword(), user.getPasswordHash())) {
            throw new RuntimeException("用户名或密码错误");
        }
        
        String token = jwtService.generateToken(user.getId().toString(), user.getRole());
        UserVO userVO = convertToUserVO(user);
        
        return LoginResponseVO.builder()
                .token(token)
                .user(userVO)
                .build();
    }

    @Transactional
    @Override
    public UserDetailVO register(RegisterRequestDTO registerRequestDTO) {
        log.info("用户注册: {}", registerRequestDTO.getUsername());
        
        // 检查用户名是否已存在
        if (userMapper.existsByUsername(registerRequestDTO.getUsername())) {
            throw new RuntimeException("用户名已存在");
        }
        
        // 检查手机号是否已存在
        if (userMapper.existsByPhone(registerRequestDTO.getPhone())) {
            throw new RuntimeException("手机号已存在");
        }
        
        // 创建用户
        User user = User.builder()
                .id(UUID.randomUUID())
                .username(registerRequestDTO.getUsername())
                .passwordHash(passwordEncoder.encode(registerRequestDTO.getPassword()))
                .name(registerRequestDTO.getName())
                .gender(registerRequestDTO.getGender())
                .birthDate(registerRequestDTO.getBirthDate())
                .phone(registerRequestDTO.getPhone())
                .email(registerRequestDTO.getEmail())
                .campusId(UUID.fromString(registerRequestDTO.getCampusId()))
                .role(registerRequestDTO.getRole())
                .createTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .deleted(false)
                .build();
        
        userMapper.insertUser(user);
        
        // 根据角色创建扩展信息
        if ("student".equals(registerRequestDTO.getRole())) {
            createStudentInfo(user.getId());
        } else if ("coach".equals(registerRequestDTO.getRole())) {
            createCoachInfo(user.getId(), registerRequestDTO);
        }
        
        return getCurrentUser(user.getId().toString());
    }

    @Override
    public UserDetailVO getCurrentUser(String userId) {
        User user = userMapper.selectById(UUID.fromString(userId));
        if (user == null) {
            throw new RuntimeException("用户不存在");
        }
        
        UserDetailVO userDetailVO = new UserDetailVO();
        userDetailVO.setUser(convertToUserVO(user));
        
        if ("student".equals(user.getRole())) {
            Student student = studentMapper.selectByUserId(user.getId());
            userDetailVO.setStudentInfo(convertToStudentInfoVO(student));
        } else if ("coach".equals(user.getRole())) {
            Coach coach = coachMapper.selectByUserId(user.getId());
            userDetailVO.setCoachInfo(convertToCoachInfoVO(coach));
        }
        
        return userDetailVO;
    }
    
    private void createStudentInfo(UUID userId) {
        Student student = Student.builder()
                .userId(userId)
                .balance(new java.math.BigDecimal("0.00"))
                .maxCoaches(2)
                .cancelCount(0)
                .createTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .deleted(false)
                .build();
        
        studentMapper.insertStudent(student);
    }
    
    private void createCoachInfo(UUID userId, RegisterRequestDTO registerRequestDTO) {
        Coach coach = Coach.builder()
                .userId(userId)
                .level(registerRequestDTO.getLevel())
                .hourlyRate(new java.math.BigDecimal("0.00"))
                .photoUrl(registerRequestDTO.getPhotoUrl())
                .awards(registerRequestDTO.getAwards())
                .maxStudents(20)
                .isApproved(false)
                .createTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .deleted(false)
                .build();
        
        coachMapper.insertCoach(coach);
    }
    
    private UserVO convertToUserVO(User user) {
        return UserVO.builder()
                .id(user.getId().toString())
                .username(user.getUsername())
                .name(user.getName())
                .gender(user.getGender())
                .birthDate(user.getBirthDate())
                .phone(user.getPhone())
                .email(user.getEmail())
                .campusId(user.getCampusId() != null ? user.getCampusId().toString() : null)
                .role(user.getRole())
                .createTime(user.getCreateTime())
                .build();
    }
    
    private StudentInfoVO convertToStudentInfoVO(Student student) {
        return StudentInfoVO.builder()
                .id(student.getUserId().toString())
                .balance(student.getBalance())
                .maxCoaches(student.getMaxCoaches())
                .cancelCount(student.getCancelCount())
                .createTime(student.getCreateTime())
                .build();
    }
    
    private CoachInfoVO convertToCoachInfoVO(Coach coach) {
        return CoachInfoVO.builder()
                .id(coach.getUserId().toString())
                .level(coach.getLevel())
                .hourlyRate(coach.getHourlyRate())
                .photoUrl(coach.getPhotoUrl())
                .awards(coach.getAwards())
                .maxStudents(coach.getMaxStudents())
                .isApproved(coach.getIsApproved())
                .createTime(coach.getCreateTime())
                .build();
    }
}