// NotificationServiceImpl.java
package com.system.service.impl;

import com.system.dto.BatchReadDTO;
import com.system.dto.NotificationQueryDTO;
import com.system.entity.Notification;
import com.system.mapper.NotificationMapper;
import com.system.service.NotificationService;
import com.system.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationMapper notificationMapper;

    @Override
    public NotificationPageVO getNotifications(NotificationQueryDTO queryDTO, String userId) {
        log.info("获取通知列表: userId={}", userId);
        
        UUID userUUID = UUID.fromString(userId);
        
        // 设置分页参数
        Integer page = queryDTO.getPage() != null ? queryDTO.getPage() : 1;
        Integer limit = queryDTO.getLimit() != null ? queryDTO.getLimit() : 20;
        long offset = (page - 1) * limit;
        
        // 查询通知列表
        List<NotificationVO> notifications = notificationMapper.selectByUserId(
            userUUID, queryDTO, offset, limit);
        
        // 查询总数
        long total = notificationMapper.countByUserId(userUUID, queryDTO);
        
        // 查询未读数量
        int unreadCount = notificationMapper.countUnreadByUserId(userUUID);
        
        // 计算总页数
        int pages = (int) Math.ceil((double) total / limit);
        
        PaginationVO pagination = PaginationVO.builder()
                .page(page)
                .limit(limit)
                .total(total)
                .pages(pages)
                .build();
        
        return NotificationPageVO.builder()
                .data(notifications)
                .unreadCount(unreadCount)
                .pagination(pagination)
                .build();
    }

    @Transactional
    @Override
    public void markAsRead(String notificationId, String userId) {
        log.info("标记通知为已读: notificationId={}, userId={}", notificationId, userId);
        
        UUID notificationUUID = UUID.fromString(notificationId);
        UUID userUUID = UUID.fromString(userId);
        
        // 验证通知是否存在且属于当前用户
        Notification notification = notificationMapper.selectById(notificationUUID);
        if (notification == null || !notification.getUserId().equals(userUUID)) {
            throw new RuntimeException("通知不存在或无权操作");
        }
        
        notificationMapper.updateReadStatus(notificationUUID, true);
    }

    @Transactional
    @Override
    public BatchReadResponseVO batchMarkAsRead(BatchReadDTO batchReadDTO, String userId) {
        log.info("批量标记通知为已读: userId={}", userId);
        
        UUID userUUID = UUID.fromString(userId);
        int processedCount = 0;
        
        if (Boolean.TRUE.equals(batchReadDTO.getMarkAllAsRead())) {
            // 标记所有通知为已读
            processedCount = notificationMapper.markAllAsRead(userUUID);
        } else if (batchReadDTO.getNotificationIds() != null && 
                  !batchReadDTO.getNotificationIds().isEmpty()) {
            // 批量标记指定通知为已读
            List<UUID> notificationIds = batchReadDTO.getNotificationIds().stream()
                    .map(UUID::fromString)
                    .collect(Collectors.toList());
            
            processedCount = notificationMapper.batchUpdateReadStatus(notificationIds, true);
        } else {
            throw new RuntimeException("请提供要标记的通知ID列表或选择标记所有");
        }
        
        return BatchReadResponseVO.builder()
                .message("通知已批量标记为已读")
                .processedCount(processedCount)
                .build();
    }

    @Override
    public UnreadCountVO getUnreadCount(String userId) {
        log.info("获取未读通知数量: userId={}", userId);
        
        UUID userUUID = UUID.fromString(userId);
        int unreadCount = notificationMapper.countUnreadByUserId(userUUID);
        
        return UnreadCountVO.builder()
                .unreadCount(unreadCount)
                .build();
    }
}