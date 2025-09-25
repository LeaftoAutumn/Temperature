// CancellationRequestVO.java
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
@ApiModel(description = "取消申请")
public class CancellationRequestVO implements Serializable {
    
    @ApiModelProperty(value = "取消申请ID")
    private String id;
    
    @ApiModelProperty(value = "预约ID")
    private String reservationId;
    
    @ApiModelProperty(value = "发起人")
    private String initiatedBy;
    
    @ApiModelProperty(value = "原因")
    private String reason;
    
    @ApiModelProperty(value = "状态")
    private String status;
    
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;
    
    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;
}