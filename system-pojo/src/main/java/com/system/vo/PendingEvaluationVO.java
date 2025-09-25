// PendingEvaluationVO.java
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
@ApiModel(description = "待评价课程信息")
public class PendingEvaluationVO implements Serializable {
    
    @ApiModelProperty(value = "课程ID")
    private String courseId;
    
    @ApiModelProperty(value = "课程时间")
    private LocalDateTime courseDate;
    
    @ApiModelProperty(value = "对方用户ID")
    private String opponentId;
    
    @ApiModelProperty(value = "对方姓名")
    private String opponentName;
    
    @ApiModelProperty(value = "对方角色")
    private String opponentRole;
    
    @ApiModelProperty(value = "课程时长")
    private Integer courseDuration;
    
    @ApiModelProperty(value = "球桌编号")
    private Integer tableNumber;
}