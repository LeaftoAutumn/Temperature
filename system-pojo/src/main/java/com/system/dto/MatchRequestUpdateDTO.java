// MatchRequestUpdateDTO.java
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
@ApiModel(description = "更新匹配申请请求参数")
public class MatchRequestUpdateDTO implements Serializable {
    
    @ApiModelProperty(value = "处理结果", required = true)
    private String status; // accepted, rejected
}