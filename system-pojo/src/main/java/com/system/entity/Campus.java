package com.system.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * 校区信息
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Campus implements Serializable {

    private static final long serialVersionUID = 1L;

    // 校区ID
    private UUID campusId;

    // 校区名称
    private String name;

    // 校区地址
    private String address;

    // 校区联系电话
    private String phone;

    // 是否为中心校区
    private Boolean center;

    // 创建时间
    private LocalDateTime createTime;

    // 更新时间
    private LocalDateTime updateTime;

    // 是否逻辑删除
    private Boolean deleted;
}
