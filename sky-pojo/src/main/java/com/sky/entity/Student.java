package com.sky.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    // 用户ID
    private UUID userId;

    // 账户余额
    private BigDecimal balance;

    // 最大可选择教练数量
    private Integer maxCoachNum;

    // 取消预约次数
    private Integer cancelCount;

    // 最后一次取消预约时间
    private LocalDateTime lastCancelTime;

    // 创建时间
    private LocalDateTime createTime;

    // 更新时间
    private LocalDateTime updateTime;

    // 是否删除
    private Boolean deleted;

}
