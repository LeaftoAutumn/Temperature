// SystemService.java
package com.system.service;

import com.system.dto.SystemActivateDTO;
import com.system.vo.SystemStatusVO;

public interface SystemService {
    
    void activateSystem(SystemActivateDTO activateDTO, String userId);
    
    SystemStatusVO getSystemStatus();
}