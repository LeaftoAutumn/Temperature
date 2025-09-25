// CoachService.java
package com.system.service;

import com.system.dto.CoachQueryDTO;
import com.system.vo.CoachDetailVO;
import com.system.vo.CoachListItemVO;
import com.system.vo.CoachPageVO;
import com.system.vo.StudentListItemVO;

import java.util.List;
import java.util.UUID;

public interface CoachService {
    
    CoachPageVO getCoaches(CoachQueryDTO queryDTO);
    
    CoachDetailVO getCoachDetail(UUID coachId);
    
    List<CoachListItemVO> getStudentCoaches(String studentId);
    
    List<StudentListItemVO> getCoachStudents(UUID coachId, String currentUserId);
}