// CampusService.java (更新)
package com.system.service;

import com.system.dto.CampusCreateRequestDTO;
import com.system.dto.CampusQueryDTO;
import com.system.dto.CampusUpdateRequestDTO;
import com.system.vo.CampusUserPageVO;
import com.system.vo.CampusVO;

import java.util.List;

public interface CampusService {
    
    List<CampusVO> listAllCampuses();
    
    CampusVO getCampusById(String campusId);
    
    CampusVO createCampus(CampusCreateRequestDTO createRequestDTO);
    
    CampusVO updateCampus(String campusId, CampusUpdateRequestDTO updateRequestDTO);
    
    CampusUserPageVO listCampusUsers(String campusId, CampusQueryDTO queryDTO);
}