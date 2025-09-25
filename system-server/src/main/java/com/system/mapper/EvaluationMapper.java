package com.system.mapper;

import com.system.dto.EvaluationQueryDTO;
import com.system.entity.Evaluation;
import com.system.vo.EvaluationDetailVO;
import com.system.vo.EvaluationStatsVO;
import com.system.vo.PendingEvaluationVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.UUID;

@Mapper
public interface EvaluationMapper {

    int insertEvaluation(Evaluation evaluation);
    
    Evaluation selectById(@Param("id") UUID id);
    
    boolean existsByCourseAndUsers(@Param("courseId") UUID courseId, 
                                 @Param("fromUserId") UUID fromUserId,
                                 @Param("toUserId") UUID toUserId);
    
    List<PendingEvaluationVO> selectPendingEvaluations(@Param("userId") UUID userId);
    
    List<EvaluationDetailVO> selectReceivedEvaluations(@Param("userId") UUID userId,
                                                     @Param("queryDTO") EvaluationQueryDTO queryDTO,
                                                     @Param("offset") long offset,
                                                     @Param("limit") int limit);
    
    long countReceivedEvaluations(@Param("userId") UUID userId,
                                @Param("queryDTO") EvaluationQueryDTO queryDTO);
    
    List<EvaluationDetailVO> selectGivenEvaluations(@Param("userId") UUID userId,
                                                  @Param("queryDTO") EvaluationQueryDTO queryDTO,
                                                  @Param("offset") long offset,
                                                  @Param("limit") int limit);
    
    long countGivenEvaluations(@Param("userId") UUID userId,
                             @Param("queryDTO") EvaluationQueryDTO queryDTO);
    
    List<EvaluationDetailVO> selectByCourseId(@Param("courseId") UUID courseId);
    
    EvaluationStatsVO selectEvaluationStats(@Param("userId") UUID userId);
    
    List<Evaluation> selectByToUserId(@Param("toUserId") UUID toUserId);
}