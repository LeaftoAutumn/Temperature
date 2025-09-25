package com.system.service;

import com.system.dto.EvaluationCreateDTO;
import com.system.dto.EvaluationQueryDTO;
import com.system.vo.*;

import java.util.List;

public interface EvaluationService {
    
    List<PendingEvaluationVO> getPendingEvaluations(String userId);
    
    EvaluationResponseVO submitEvaluation(EvaluationCreateDTO createDTO, String userId);
    
    EvaluationPageVO getReceivedEvaluations(EvaluationQueryDTO queryDTO, String userId);
    
    EvaluationPageVO getGivenEvaluations(EvaluationQueryDTO queryDTO, String userId);
    
    CourseEvaluationVO getCourseEvaluations(String courseId, String userId);
    
    EvaluationStatsVO getEvaluationStats(String userId);
}