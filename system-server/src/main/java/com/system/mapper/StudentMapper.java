package com.system.mapper;

import com.system.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.UUID;

@Mapper
public interface StudentMapper {

    Student selectByUserId(@Param("userId") UUID userId);

    int insertStudent(Student student);

    BigDecimal getBalance(@Param("userId") UUID userId);

    int updateBalance(@Param("userId") UUID userId, @Param("amount") BigDecimal amount);

    Integer getMonthlyCancelCount(@Param("userId") UUID userId, @Param("yearMonth") String yearMonth);

    int updateCancelCount(@Param("userId") UUID userId, @Param("cancelCount") int cancelCount,
                          @Param("lastCancelMonth") int lastCancelMonth);

    int updateStudent(Student student);
}