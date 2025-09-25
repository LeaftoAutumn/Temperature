package com.system.mapper;

import com.system.entity.Coach;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.UUID;

@Mapper
public interface CoachMapper {

    Coach selectByUserId(@Param("userId") UUID userId);

    int insertCoach(Coach coach);

    BigDecimal getHourlyRate(@Param("userId") UUID userId);

    int updateCoach(Coach coach);
}