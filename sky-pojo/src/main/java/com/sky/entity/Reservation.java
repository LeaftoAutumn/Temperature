package com.sky.entity;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Reservation implements Serializable {

    private static final long serialVersionUID = 1L;

    // 预约ID
    private String reservationId;

    // 课程ID
    private String courseId;

    // 学生ID
    private String studentId;

    // 教练ID
    private String coachId;

    // 操作类型
    private Action action;

    // 预约状态
    private Status status;

    // 创建时间
    private String createTime;

    // 更新时间
    private String updateTime;

    // 是否删除
    private Boolean deleted;

    @Getter
    public enum Action {
        RESERVE("reserve", "预约"),
        CANCEL("cancel", "取消");

        private final String action;
        private final String description;

        Action(String action, String description) {
            this.action = action;
            this.description = description;
        }
    }

    @Getter
    public enum Status {
        PENDING("pending", "待确认"),
        CONFIRMED("confirmed", "已确认"),
        REJECTED("rejected", "已拒绝");

        private final String status;
        private final String description;

        Status(String status, String description) {
            this.status = status;
            this.description = description;
        }
    }
}
