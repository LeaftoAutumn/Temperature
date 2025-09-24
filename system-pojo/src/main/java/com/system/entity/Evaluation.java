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
public class Evaluation implements Serializable {

    private static final long serialVersionUID = 1L;

    // 课程评价ID
    private UUID evaluationId;

    // 课程ID
    private UUID courseId;

    // 评价人ID
    private UUID fromUserId;

    // 被评价人ID
    private UUID toUserId;

    // 评价内容
    private String content;

    // 评分
    private Integer rating;

    // 创建时间
    private LocalDateTime createTime;

    // 更新时间
    private LocalDateTime updateTime;

    // 是否删除
    private Boolean deleted;
}
