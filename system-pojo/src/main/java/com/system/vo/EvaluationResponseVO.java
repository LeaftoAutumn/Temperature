// EvaluationResponseVO.java
package com.system.vo;

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
@ApiModel(description = "评价提交响应")
public class EvaluationResponseVO implements Serializable {
    
    @ApiModelProperty(value = "评价记录ID")
    private String id;
    
    @ApiModelProperty(value = "消息")
    private String message;
}