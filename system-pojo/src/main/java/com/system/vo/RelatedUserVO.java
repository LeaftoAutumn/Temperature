// RelatedUserVO.java
package com.system.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "相关用户信息")
public class RelatedUserVO implements Serializable {
    
    @ApiModelProperty(value = "用户ID")
    private UUID id;
    
    @ApiModelProperty(value = "姓名")
    private String name;
    
    @ApiModelProperty(value = "角色")
    private String role;
}