package com.system.entity;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * 教练学员双向选择表
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CoachStudentMatch implements Serializable {

    private static final long serialVersionUID = 1L;

    // 双向选择选择ID
    private UUID matchId;

    // 教练ID
    private UUID coachId;

    // 学员ID
    private UUID studentId;

    // 状态：pending（待匹配）、accepted（已匹配）、rejected（已拒绝）
    private Status status;

    // 创建时间
    private LocalDateTime createTime;

    // 更新时间
    private LocalDateTime updateTime;

    // 是否逻辑删除
    private Boolean deleted;

    @Getter
    public enum Status {
        PENDING("pending", "待匹配"),
        ACCEPTED("accepted", "已匹配"),
        REJECTED("rejected", "已拒绝");

        private final String status;
        private final String description;

        Status(String status, String description) {
            this.status = status;
            this.description = description;
        }
    }
}
