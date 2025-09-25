// MatchRequestCreateDTO.java
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
@ApiModel(description = "双选申请请求参数")
public class MatchRequestCreateDTO implements Serializable {
    
    @ApiModelProperty(value = "学员ID", required = true)
    private UUID studentId;
    
    @ApiModelProperty(value = "教练ID", required = true)
    private UUID coachId;
}