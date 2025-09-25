package com.system.mapper;

import com.system.entity.Reservation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.UUID;

@Mapper
public interface ReservationMapper {

    int insertReservation(Reservation reservation);
    
    Reservation selectById(@Param("id") UUID id);
    
    int updateReservationStatus(@Param("id") UUID id, @Param("status") String status);
    
    boolean existsCoachStudentMatch(@Param("studentId") UUID studentId, @Param("coachId") UUID coachId);
}