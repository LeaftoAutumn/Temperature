// SystemLog.java
package com.system.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SystemLog implements Serializable {
    private static final long serialVersionUID = 1L;

    private UUID id;
    private UUID userId;
    private String userName;
    private String userRole;
    private String action;
    private String actionType;
    private String ipAddress;
    private String userAgent;
    private String details; // JSON格式存储
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Boolean deleted;
}