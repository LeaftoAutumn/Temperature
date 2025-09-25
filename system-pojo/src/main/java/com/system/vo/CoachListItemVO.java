// CoachListItemVO.java
package com.system.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "教练列表项信息")
public class CoachListItemVO implements Serializable {
    
    @ApiModelProperty(value = "用户ID")
    private String userId;
    
    @ApiModelProperty(value = "姓名")
    private String name;
    
    @ApiModelProperty(value = "性别")
    private String gender;
    
    @ApiModelProperty(value = "年龄")
    private Integer age;
    
    @ApiModelProperty(value = "教练等级")
    private String level;
    
    @ApiModelProperty(value = "每小时费率")
    private BigDecimal hourlyRate;
    
    @ApiModelProperty(value = "照片URL")
    private String photoUrl;
    
    @ApiModelProperty(value = "获奖记录")
    private String awards;
}