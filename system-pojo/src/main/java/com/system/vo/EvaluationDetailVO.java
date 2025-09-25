// EvaluationDetailVO.java
package com.system.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "评价详情")
public class EvaluationDetailVO implements Serializable {
    
    @ApiModelProperty(value = "评价ID")
    private String id;
    
    @ApiModelProperty(value = "课程ID")
    private String courseId;
    
    @ApiModelProperty(value = "评价人ID")
    private String fromUserId;
    
    @ApiModelProperty(value = "评价人姓名")
    private String fromUserName;
    
    @ApiModelProperty(value = "评价人角色")
    private String fromUserRole;
    
    @ApiModelProperty(value = "被评价人ID")
    private String toUserId;
    
    @ApiModelProperty(value = "被评价人姓名")
    private String toUserName;
    
    @ApiModelProperty(value = "被评价人角色")
    private String toUserRole;
    
    @ApiModelProperty(value = "评分")
    private Integer rating;
    
    @ApiModelProperty(value = "评价内容")
    private String content;
    
    @ApiModelProperty(value = "评价时间")
    private LocalDateTime createTime;
    
    @ApiModelProperty(value = "课程基本信息")
    private CourseBasicInfoVO courseInfo;
}