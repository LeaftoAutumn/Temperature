// UnreadCountVO.java
package com.system.vo;

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
@ApiModel(description = "未读数量响应")
public class UnreadCountVO implements Serializable {
    
    @ApiModelProperty(value = "未读通知总数")
    private Integer unreadCount;
}