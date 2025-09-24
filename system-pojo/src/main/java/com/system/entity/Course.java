package com.system.entity;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Course implements Serializable {

    private static final long serialVersionUID = 1L;

    // 课程id
    private UUID courseId;

    // 教练id
    private UUID coachId;

    // 学员id
    private UUID studentId;

    // 课程开始时间
    private LocalDateTime startTime;

    // 课程结束时间
    private LocalDateTime endTime;

    // 课程价格
    private BigDecimal price;

    // 课程状态
    private Status status;

    // 教学场地
    private String teachingVenue;

    // 创建时间
    private LocalDateTime createTime;

    // 更新时间
    private LocalDateTime updateTime;

    // 是否删除
    private Boolean deleted;

    @Getter
    public enum Status {
        PENDING("pending", "待确认"),
        CONFIRMED("confirmed", "已确认"),
        CANCELLED("cancelled", "已取消"),
        COMPLETED("completed", "已完成");

        private final String status;
        private final String description;

        Status(String status, String description) {
            this.status = status;
            this.description = description;
        }
    }
}
