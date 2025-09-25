// SystemLogServiceImpl.java
package com.system.service.impl;

import com.system.dto.SystemLogQueryDTO;
import com.system.entity.SystemLog;
import com.system.entity.User;
import com.system.mapper.SystemLogMapper;
import com.system.mapper.UserMapper;
import com.system.service.SystemLogService;
import com.system.vo.PaginationVO;
import com.system.vo.SystemLogPageVO;
import com.system.vo.SystemLogVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class SystemLogServiceImpl implements SystemLogService {

    @Autowired
    private SystemLogMapper systemLogMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public SystemLogPageVO getSystemLogs(SystemLogQueryDTO queryDTO) {
        log.info("查询系统日志");
        
        // 设置分页参数
        Integer page = queryDTO.getPage() != null ? queryDTO.getPage() : 1;
        Integer limit = queryDTO.getLimit() != null ? queryDTO.getLimit() : 20;
        long offset = (page - 1) * limit;
        
        // 查询日志列表
        List<SystemLogVO> logs = systemLogMapper.selectSystemLogs(queryDTO, offset, limit);
        
        // 查询总数
        long total = systemLogMapper.countSystemLogs(queryDTO);
        
        // 计算总页数
        int pages = (int) Math.ceil((double) total / limit);
        
        PaginationVO pagination = PaginationVO.builder()
                .page(page)
                .limit(limit)
                .total(total)
                .pages(pages)
                .build();
        
        return SystemLogPageVO.builder()
                .data(logs)
                .pagination(pagination)
                .build();
    }

    @Override
    public void createSystemLog(String userId, String action, String actionType, 
                               String ipAddress, String userAgent, Object details) {
        try {
            UUID userUUID = UUID.fromString(userId);
            User user = userMapper.selectById(userUUID);
            
            SystemLog systemLog = SystemLog.builder()
                    .id(UUID.randomUUID())
                    .userId(userUUID)
                    .userName(user != null ? user.getName() : "未知用户")
                    .userRole(user != null ? user.getRole() : "未知角色")
                    .action(action)
                    .actionType(actionType)
                    .ipAddress(ipAddress)
                    .userAgent(userAgent)
                    .details(details != null ? details.toString() : null)
                    .createTime(LocalDateTime.now())
                    .updateTime(LocalDateTime.now())
                    .deleted(false)
                    .build();
            
            systemLogMapper.insertSystemLog(systemLog);
        } catch (Exception e) {
            log.error("创建系统日志失败: {}", e.getMessage());
            // 不抛出异常，避免影响主要业务流程
        }
    }
}