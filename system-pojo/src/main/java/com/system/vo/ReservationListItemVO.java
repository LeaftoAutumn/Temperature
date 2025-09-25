// ReservationListItemVO.java
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
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "预约列表项")
public class ReservationListItemVO implements Serializable {
    
    @ApiModelProperty(value = "预约ID")
    private UUID id;
    
    @ApiModelProperty(value = "学员ID")
    private UUID studentId;
    
    @ApiModelProperty(value = "学员姓名")
    private String studentName;
    
    @ApiModelProperty(value = "教练ID")
    private UUID coachId;
    
    @ApiModelProperty(value = "教练姓名")
    private String coachName;
    
    @ApiModelProperty(value = "开始时间")
    private LocalDateTime startTime;
    
    @ApiModelProperty(value = "结束时间")
    private LocalDateTime endTime;
    
    @ApiModelProperty(value = "时长")
    private Integer duration;
    
    @ApiModelProperty(value = "价格")
    private BigDecimal price;
    
    @ApiModelProperty(value = "状态")
    private String status;
    
    @ApiModelProperty(value = "球桌编号")
    private Integer tableNumber;
    
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;
}