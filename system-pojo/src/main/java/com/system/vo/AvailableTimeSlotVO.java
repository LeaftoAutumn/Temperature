// AvailableTimeSlotVO.java
package com.system.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "可用时段信息")
public class AvailableTimeSlotVO implements Serializable {
    
    @ApiModelProperty(value = "开始时间")
    private LocalDateTime startTime;
    
    @ApiModelProperty(value = "结束时间")
    private LocalDateTime endTime;
    
    @ApiModelProperty(value = "可用球桌编号列表")
    private List<Integer> availableTables;
}