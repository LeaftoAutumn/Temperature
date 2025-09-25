// TimetableItemVO.java
package com.system.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "课表项")
public class TimetableItemVO implements Serializable {
    
    @ApiModelProperty(value = "ID")
    private String id;
    
    @ApiModelProperty(value = "日期")
    private LocalDate date;
    
    @ApiModelProperty(value = "开始时间")
    private LocalDateTime startTime;
    
    @ApiModelProperty(value = "结束时间")
    private LocalDateTime endTime;
    
    @ApiModelProperty(value = "类型")
    private String type;
    
    @ApiModelProperty(value = "标题")
    private String title;
    
    @ApiModelProperty(value = "地点")
    private String location;
    
    @ApiModelProperty(value = "球桌编号")
    private Integer tableNumber;
    
    @ApiModelProperty(value = "相关用户")
    private RelatedUserVO withUser;
    
    @ApiModelProperty(value = "状态")
    private String status;
}