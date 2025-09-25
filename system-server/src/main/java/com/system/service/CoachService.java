// CoachService.java
package com.system.service;

import com.system.dto.CoachQueryDTO;
import com.system.vo.CoachDetailVO;
import com.system.vo.CoachListItemVO;
import com.system.vo.CoachPageVO;
import com.system.vo.StudentListItemVO;

import java.util.List;

public interface CoachService {
    
    CoachPageVO getCoaches(CoachQueryDTO queryDTO);
    
    CoachDetailVO getCoachDetail(String coachId);
    
    List<CoachListItemVO> getStudentCoaches(String studentId);
    
    List<StudentListItemVO> getCoachStudents(String coachId, String currentUserId);
}