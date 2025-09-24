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
public class Tournament implements Serializable {

    private static final long serialVersionUID = 1L;

    // 比赛ID
    private UUID tournamentId;

    // 比赛名称
    private String name;

    // 比赛日期
    private LocalDateTime date;
    
    // 比赛组别
    private GroupType groupType;
    
    // 比赛形式
    private Format format;

    // 比赛状态
    private Status status;

    // 报名费用
    private BigDecimal entryFee;

    // 比赛地点
    private String location;

    // 报名截止日期
    private String registrationDeadline;

    // 创建时间
    private String createTime;

    // 更新时间
    private String updateTime;

    // 是否删除
    private Boolean deleted;
    
    @Getter
    public enum GroupType {
        A("A", "甲组"),
        B("B", "乙组"),
        C("C", "丙组");

        private final String groupType;
        private final String description;

        GroupType(String groupType, String description) {
            this.groupType = groupType;
            this.description = description;
        }
    }
    
    @Getter
    public enum Format {
        ROUND_ROBIN("round_robin", "循环赛"),
        KNOCKOUT("knockout", "淘汰赛");

        private final String format;
        private final String description;

        Format(String format, String description) {
            this.format = format;
            this.description = description;
        }
    }

    @Getter
    public enum Status {
        UPCOMING("upcoming", "比赛即将开始"),
        ONGOING("ongoing", "比赛进行中"),
        COMPLETED("completed", "比赛已结束");

        private final String status;
        private final String description;

        Status(String status, String description) {
            this.status = status;
            this.description = description;
        }
    }
}
