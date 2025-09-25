// TournamentMatchVO.java
package com.system.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "月赛比赛信息")
public class TournamentMatchVO implements Serializable {
    
    @ApiModelProperty(value = "比赛ID")
    private String id;
    
    @ApiModelProperty(value = "月赛ID")
    private String tournamentId;
    
    @ApiModelProperty(value = "组别名称")
    private String groupName;
    
    @ApiModelProperty(value = "选手1ID")
    private String player1Id;
    
    @ApiModelProperty(value = "选手2ID")
    private String player2Id;
    
    @ApiModelProperty(value = "选手1姓名")
    private String player1Name;
    
    @ApiModelProperty(value = "选手2姓名")
    private String player2Name;
    
    @ApiModelProperty(value = "计划比赛时间")
    private LocalDateTime scheduledTime;
    
    @ApiModelProperty(value = "比赛结果")
    private String result;
    
    @ApiModelProperty(value = "获胜者ID")
    private String winnerId;
    
    @ApiModelProperty(value = "比赛状态")
    private String status;
}