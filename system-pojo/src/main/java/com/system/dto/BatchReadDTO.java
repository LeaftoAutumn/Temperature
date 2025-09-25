// BatchReadDTO.java
package com.system.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "批量已读请求参数")
public class BatchReadDTO implements Serializable {
    
    @ApiModelProperty(value = "通知ID列表")
    private List<String> notificationIds;
    
    @ApiModelProperty(value = "是否标记所有通知为已读")
    private Boolean markAllAsRead;
}