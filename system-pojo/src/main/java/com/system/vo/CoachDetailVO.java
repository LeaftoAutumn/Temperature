// CoachDetailVO.java
package com.system.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "教练详细信息")
public class CoachDetailVO implements Serializable {
    
    @ApiModelProperty(value = "用户ID")
    private UUID userId;
    
    @ApiModelProperty(value = "姓名")
    private String name;
    
    @ApiModelProperty(value = "性别")
    private String gender;

    @ApiModelProperty(value = "出生日期")
    private LocalDateTime birthDate;
    
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
    
    @ApiModelProperty(value = "手机号")
    private String phone;
    
    @ApiModelProperty(value = "邮箱")
    private String email;
    
    @ApiModelProperty(value = "最多学员数量")
    private Integer maxStudents;
    
    @ApiModelProperty(value = "当前学员数量")
    private Integer currentStudents;
    
    @ApiModelProperty(value = "是否通过审核")
    private Boolean isApproved;
    
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdTime;
}