// UserDetailVO.java
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
@ApiModel(description = "用户详细信息")
public class UserDetailVO implements Serializable {
    
    @ApiModelProperty(value = "用户基本信息")
    private UserVO user;
    
    @ApiModelProperty(value = "学员信息")
    private StudentInfoVO studentInfo;
    
    @ApiModelProperty(value = "教练信息")
    private CoachInfoVO coachInfo;
}