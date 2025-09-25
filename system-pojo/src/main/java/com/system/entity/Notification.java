// Notification.java
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
public class Notification implements Serializable {
    private static final long serialVersionUID = 1L;

    private UUID id;
    private UUID userId;
    private String title;
    private String content;
    private String type;
    private UUID relatedId;
    private String relatedType;
    private Boolean isRead;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Boolean deleted;
    private String metadata; // JSON格式存储
}