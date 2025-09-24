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
public class TournamentRegistration implements Serializable {

    private static final long serialVersionUID = 1L;

    // 报名ID
    private UUID registrationId;

    // 学生ID
    private UUID studentId;

    // 比赛ID
    private UUID tournamentId;

    // 是否支付报名费
    private Boolean feePaid;

    // 创建时间
    private String createTime;

    // 更新时间
    private String updateTime;

    // 是否删除
    private Boolean deleted;
}
