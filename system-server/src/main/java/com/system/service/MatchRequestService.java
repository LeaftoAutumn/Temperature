// MatchRequestService.java
package com.system.service;

import com.system.dto.MatchRequestCreateDTO;
import com.system.dto.MatchRequestQueryDTO;
import com.system.dto.MatchRequestUpdateDTO;
import com.system.vo.MatchRequestPageVO;
import com.system.vo.MatchRequestVO;

public interface MatchRequestService {
    
    MatchRequestVO createMatchRequest(MatchRequestCreateDTO createDTO);
    
    MatchRequestPageVO getMatchRequests(MatchRequestQueryDTO queryDTO, String currentUserId, String userRole);
    
    MatchRequestVO updateMatchRequest(String requestId, MatchRequestUpdateDTO updateDTO, String currentUserId, String userRole);
}