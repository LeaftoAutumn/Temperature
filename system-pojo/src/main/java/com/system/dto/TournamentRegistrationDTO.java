// TournamentRegistrationDTO.java
package com.system.dto;

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
@ApiModel(description = "月赛报名请求参数")
public class TournamentRegistrationDTO implements Serializable {
    
    @ApiModelProperty(value = "月赛ID", required = true)
    private UUID tournamentId;
}