// NotificationService.java
package com.system.service;

import com.system.dto.BatchReadDTO;
import com.system.dto.NotificationQueryDTO;
import com.system.vo.*;

import java.util.List;

public interface NotificationService {
    
    NotificationPageVO getNotifications(NotificationQueryDTO queryDTO, String userId);
    
    void markAsRead(String notificationId, String userId);
    
    BatchReadResponseVO batchMarkAsRead(BatchReadDTO batchReadDTO, String userId);
    
    UnreadCountVO getUnreadCount(String userId);
}