// CoachInfoVO.java
package com.system.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "教练扩展信息")
public class CoachInfoVO implements Serializable {
    
    @ApiModelProperty(value = "教练ID")
    private String id;
    
    @ApiModelProperty(value = "教练等级")
    private String level;
    
    @ApiModelProperty(value = "每小时费率")
    private BigDecimal hourlyRate;
    
    @ApiModelProperty(value = "照片URL")
    private String photoUrl;
    
    @ApiModelProperty(value = "获奖记录")
    private String awards;
    
    @ApiModelProperty(value = "最多学员数量")
    private Integer maxStudents;
    
    @ApiModelProperty(value = "是否通过审核")
    private Boolean isApproved;
    
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;
}