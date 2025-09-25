// MatchRequestDetailVO.java
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
@ApiModel(description = "匹配申请详情")
public class MatchRequestDetailVO implements Serializable {
    
    @ApiModelProperty(value = "申请ID")
    private String id;
    
    @ApiModelProperty(value = "学员ID")
    private String studentId;
    
    @ApiModelProperty(value = "学员姓名")
    private String studentName;
    
    @ApiModelProperty(value = "教练ID")
    private String coachId;
    
    @ApiModelProperty(value = "教练姓名")
    private String coachName;
    
    @ApiModelProperty(value = "申请状态")
    private String status;
    
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdTime;
    
    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updatedTime;
}