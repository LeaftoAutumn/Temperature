// TournamentRegistrationResponseVO.java
package com.system.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(description = "月赛报名响应")
public class TournamentRegistrationResponseVO implements Serializable {
    
    @ApiModelProperty(value = "报名记录ID")
    private UUID id;
    
    @ApiModelProperty(value = "响应消息")
    private String message;
}