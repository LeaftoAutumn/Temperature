// MatchRequestQueryDTO.java
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
@ApiModel(description = "匹配申请查询参数")
public class MatchRequestQueryDTO implements Serializable {
    
    @ApiModelProperty(value = "申请状态")
    private String status; // pending, accepted, rejected
    
    @ApiModelProperty(value = "页码")
    private Integer page;
    
    @ApiModelProperty(value = "每页数量")
    private Integer limit;
}