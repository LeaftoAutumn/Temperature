package com.sky.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class systemLog implements Serializable {

    private static final long serialVersionUID = 1L;

    // 日志ID
    private UUID logId;

    // 操作用户ID
    private UUID userId;

    // 操作描述
    private String action;

    // IP地址
    private String ipAddress;

    // 创建时间
    private String createTime;

    // 更新时间
    private String updateTime;

    // 是否删除
    private Boolean deleted;
}
