package com.system.mapper;

import com.system.entity.CoachStudentMatch;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.UUID;

@Mapper
public interface CoachStudentMatchMapper {

    CoachStudentMatch selectByStudentAndCoach(@Param("studentId") UUID studentId, @Param("coachId") UUID coachId);

    int insertMatch(CoachStudentMatch match);

    int updateMatchStatus(@Param("studentId") UUID studentId, @Param("coachId") UUID coachId, @Param("status") String status);

    List<CoachStudentMatch> selectByStudentId(@Param("studentId") UUID studentId);

    List<CoachStudentMatch> selectByCoachId(@Param("coachId") UUID coachId);
}