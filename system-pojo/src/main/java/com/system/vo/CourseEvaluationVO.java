// CourseEvaluationVO.java
package com.system.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "课程评价响应")
public class CourseEvaluationVO implements Serializable {
    
    @ApiModelProperty(value = "课程信息")
    private CourseBasicInfoVO courseInfo;
    
    @ApiModelProperty(value = "评价列表")
    private List<EvaluationDetailVO> evaluations;
}