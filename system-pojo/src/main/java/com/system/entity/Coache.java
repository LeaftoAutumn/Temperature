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
public class Coache implements Serializable {

    private static final long serialVersionUID = 1L;

    // 教练ID
    private UUID coachId;

    // 水平级别
    private Level level;

    // 每小时费率
    private BigDecimal hourlyRate;

    // 照片地址
    private String photoUrl;

    // 获奖记录
    private String awards;

    // 最多学生数量
    private Integer maxStudents;

    // 是否通过审核
    private Boolean approved;

    // 创建时间
    private LocalDateTime createdTime;

    // 更新时间
    private LocalDateTime updatedTime;

    // 是否删除
    private Boolean deleted;

    @Getter
    public enum Level {
        PRIMARY("Primary", "初级水平"),
        INTERMEDIATE("Intermediate", "中级水平"),
        ADVANCED("Advanced", "高级水平");

        private final String level;
        private final String description;

        Level(String level, String description) {
            this.level = level;
            this.description = description;
        }
    }
}
