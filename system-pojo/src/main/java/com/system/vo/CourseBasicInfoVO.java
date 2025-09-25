// CourseBasicInfoVO.java
package com.system.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "课程基本信息")
public class CourseBasicInfoVO implements Serializable {
    
    @ApiModelProperty(value = "课程ID")
    private UUID id;
    
    @ApiModelProperty(value = "开始时间")
    private LocalDateTime startTime;
    
    @ApiModelProperty(value = "结束时间")
    private LocalDateTime endTime;
    
    @ApiModelProperty(value = "球桌编号")
    private Integer tableNumber;
    
    @ApiModelProperty(value = "课程状态")
    private String status;
}