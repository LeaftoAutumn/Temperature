// SystemLogService.java
package com.system.service;

import com.system.dto.SystemLogQueryDTO;
import com.system.vo.SystemLogPageVO;

public interface SystemLogService {
    
    SystemLogPageVO getSystemLogs(SystemLogQueryDTO queryDTO);
    
    void createSystemLog(String userId, String action, String actionType, String ipAddress, 
                        String userAgent, Object details);
}