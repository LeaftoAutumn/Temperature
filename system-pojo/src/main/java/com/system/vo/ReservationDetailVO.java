// ReservationDetailVO.java
package com.system.vo;

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
@ApiModel(description = "预约详情")
public class ReservationDetailVO implements Serializable {
    
    @ApiModelProperty(value = "预约基本信息")
    private ReservationListItemVO reservation;
    
    @ApiModelProperty(value = "课程ID")
    private UUID courseId;
    
    @ApiModelProperty(value = "是否可以取消")
    private Boolean canCancel;
}