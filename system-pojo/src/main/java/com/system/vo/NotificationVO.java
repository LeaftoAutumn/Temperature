// NotificationVO.java
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
@ApiModel(description = "通知信息")
public class NotificationVO implements Serializable {
    
    @ApiModelProperty(value = "通知ID")
    private String id;
    
    @ApiModelProperty(value = "接收用户ID")
    private String userId;
    
    @ApiModelProperty(value = "通知标题")
    private String title;
    
    @ApiModelProperty(value = "通知内容")
    private String content;
    
    @ApiModelProperty(value = "通知类型")
    private String type;
    
    @ApiModelProperty(value = "相关业务ID")
    private String relatedId;
    
    @ApiModelProperty(value = "相关业务类型")
    private String relatedType;
    
    @ApiModelProperty(value = "是否已读")
    private Boolean isRead;
    
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;
    
    @ApiModelProperty(value = "附加元数据")
    private Object metadata;
}