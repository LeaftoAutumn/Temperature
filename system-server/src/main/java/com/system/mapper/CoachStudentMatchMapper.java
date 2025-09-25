package com.system.mapper;

import com.system.dto.MatchRequestQueryDTO;
import com.system.entity.CoachStudentMatch;
import com.system.vo.MatchRequestDetailVO;
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

    int insertMatchRequest(CoachStudentMatch match);

    CoachStudentMatch selectById(@Param("id") UUID id);

    int updateMatchStatus(@Param("id") UUID id, @Param("status") String status);

    List<MatchRequestDetailVO> selectMatchRequests(@Param("userId") UUID userId,
                                                   @Param("userRole") String userRole,
                                                   @Param("queryDTO") MatchRequestQueryDTO queryDTO,
                                                   @Param("offset") long offset,
                                                   @Param("limit") int limit);

    long countMatchRequests(@Param("userId") UUID userId,
                            @Param("userRole") String userRole,
                            @Param("queryDTO") MatchRequestQueryDTO queryDTO);

    int countAcceptedMatchesByStudent(@Param("studentId") UUID studentId);

    int countAcceptedMatchesByCoach(@Param("coachId") UUID coachId);

    List<CoachStudentMatch> selectAcceptedMatchesByStudent(@Param("studentId") UUID studentId);

    List<CoachStudentMatch> selectAcceptedMatchesByCoach(@Param("coachId") UUID coachId);
}