// MatchResultUpdateDTO.java
package com.system.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "更新比赛结果请求参数")
public class MatchResultUpdateDTO implements Serializable {
    
    @ApiModelProperty(value = "比赛结果", required = true)
    private String result;
    
    @ApiModelProperty(value = "获胜者ID", required = true)
    private String winnerId;
    
    @ApiModelProperty(value = "比赛状态", required = true)
    private String status;
}