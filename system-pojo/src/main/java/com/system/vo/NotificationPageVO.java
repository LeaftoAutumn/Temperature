// NotificationPageVO.java
package com.system.vo;

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
@ApiModel(description = "通知分页响应")
public class NotificationPageVO implements Serializable {
    
    @ApiModelProperty(value = "通知列表")
    private List<NotificationVO> data;
    
    @ApiModelProperty(value = "未读通知总数")
    private Integer unreadCount;
    
    @ApiModelProperty(value = "分页信息")
    private PaginationVO pagination;
}