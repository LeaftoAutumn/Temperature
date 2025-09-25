// NotificationController.java
package com.system.controller;

import com.system.dto.BatchReadDTO;
import com.system.dto.NotificationQueryDTO;
import com.system.service.NotificationService;
import com.system.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/notifications")
@Slf4j
@Api(tags = "消息通知管理")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping
    @ApiOperation("获取通知列表")
    public NotificationPageVO listNotifications(@Valid NotificationQueryDTO queryDTO,
                                              HttpServletRequest request) {
        String currentUserId = (String) request.getAttribute("userId");
        log.info("获取通知列表: currentUserId={}", currentUserId);
        return notificationService.getNotifications(queryDTO, currentUserId);
    }

    @PutMapping("/{notificationId}/read")
    @ApiOperation("标记通知为已读")
    public void markNotificationAsRead(@PathVariable String notificationId,
                                     HttpServletRequest request) {
        String currentUserId = (String) request.getAttribute("userId");
        log.info("标记通知为已读: notificationId={}, currentUserId={}", notificationId, currentUserId);
        notificationService.markAsRead(notificationId, currentUserId);
    }

    @PostMapping("/batch-read")
    @ApiOperation("批量标记通知为已读")
    public BatchReadResponseVO batchMarkNotificationsAsRead(@Valid @RequestBody BatchReadDTO batchReadDTO,
                                                          HttpServletRequest request) {
        String currentUserId = (String) request.getAttribute("userId");
        log.info("批量标记通知为已读: currentUserId={}", currentUserId);
        return notificationService.batchMarkAsRead(batchReadDTO, currentUserId);
    }

    @GetMapping("/unread-count")
    @ApiOperation("获取未读通知数量")
    public UnreadCountVO getUnreadNotificationsCount(HttpServletRequest request) {
        String currentUserId = (String) request.getAttribute("userId");
        log.info("获取未读通知数量: currentUserId={}", currentUserId);
        return notificationService.getUnreadCount(currentUserId);
    }
}