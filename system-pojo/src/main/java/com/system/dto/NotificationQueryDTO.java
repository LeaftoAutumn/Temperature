// NotificationQueryDTO.java
package com.system.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "通知查询参数")
public class NotificationQueryDTO implements Serializable {
    
    @ApiModelProperty(value = "是否已读")
    private Boolean isRead;
    
    @ApiModelProperty(value = "通知类型")
    private String type;
    
    @ApiModelProperty(value = "页码")
    private Integer page;
    
    @ApiModelProperty(value = "每页数量")
    private Integer limit;
}