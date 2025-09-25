// SystemLogQueryDTO.java
package com.system.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "系统日志查询参数")
public class SystemLogQueryDTO implements Serializable {
    
    @ApiModelProperty(value = "用户ID")
    private UUID userId;
    
    @ApiModelProperty(value = "操作类型")
    private String actionType;
    
    @ApiModelProperty(value = "开始日期")
    private LocalDate startDate;
    
    @ApiModelProperty(value = "结束日期")
    private LocalDate endDate;
    
    @ApiModelProperty(value = "页码")
    private Integer page;
    
    @ApiModelProperty(value = "每页数量")
    private Integer limit;
}