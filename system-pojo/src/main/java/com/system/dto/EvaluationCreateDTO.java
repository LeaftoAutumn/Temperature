// EvaluationCreateDTO.java
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
@ApiModel(description = "创建评价请求参数")
public class EvaluationCreateDTO implements Serializable {
    
    @ApiModelProperty(value = "课程ID", required = true)
    private UUID courseId;
    
    @ApiModelProperty(value = "被评价用户ID", required = true)
    private UUID toUserId;
    
    @ApiModelProperty(value = "评分", required = true)
    private Integer rating;
    
    @ApiModelProperty(value = "评价内容", required = true)
    private String content;
}