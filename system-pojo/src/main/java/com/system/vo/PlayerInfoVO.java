// PlayerInfoVO.java
package com.system.vo;

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
@ApiModel(description = "选手信息")
public class PlayerInfoVO implements Serializable {
    
    @ApiModelProperty(value = "用户ID")
    private UUID userId;
    
    @ApiModelProperty(value = "姓名")
    private String name;
    
    @ApiModelProperty(value = "性别")
    private String gender;
    
    @ApiModelProperty(value = "年龄")
    private Integer age;
}