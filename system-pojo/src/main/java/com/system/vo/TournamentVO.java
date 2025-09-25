// TournamentVO.java
package com.system.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "月赛信息")
public class TournamentVO implements Serializable {
    
    @ApiModelProperty(value = "月赛ID")
    private UUID id;
    
    @ApiModelProperty(value = "月赛名称")
    private String name;
    
    @ApiModelProperty(value = "比赛日期")
    private LocalDate eventDate;
    
    @ApiModelProperty(value = "分组类型")
    private String groupType;
    
    @ApiModelProperty(value = "赛制")
    private String format;
    
    @ApiModelProperty(value = "比赛状态")
    private String status;
    
    @ApiModelProperty(value = "报名费")
    private BigDecimal entryFee;
    
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdTime;
}