// EvaluationQueryDTO.java
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
@ApiModel(description = "评价查询参数")
public class EvaluationQueryDTO implements Serializable {
    
    @ApiModelProperty(value = "页码", example = "1")
    private Integer page;
    
    @ApiModelProperty(value = "每页数量", example = "20")
    private Integer limit;
    
    @ApiModelProperty(value = "评分筛选")
    private Integer rating;
}