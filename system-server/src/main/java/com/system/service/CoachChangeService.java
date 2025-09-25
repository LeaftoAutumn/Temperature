// CoachChangeService.java
package com.system.service;

import com.system.dto.CoachChangeRequestCreateDTO;
import com.system.vo.CoachChangeRequestVO;

public interface CoachChangeService {
    
    CoachChangeRequestVO createCoachChangeRequest(CoachChangeRequestCreateDTO createDTO, String currentUserId);
}