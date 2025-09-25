// CoachQueryDTO.java
package com.system.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "教练查询参数")
public class CoachQueryDTO implements Serializable {
    
    @ApiModelProperty(value = "校区ID", required = true)
    private UUID campusId;
    
    @ApiModelProperty(value = "教练姓名")
    private String name;
    
    @ApiModelProperty(value = "性别")
    private String gender;
    
    @ApiModelProperty(value = "最小年龄")
    private Integer minAge;
    
    @ApiModelProperty(value = "最大年龄")
    private Integer maxAge;
    
    @ApiModelProperty(value = "教练等级")
    private String level;
    
    @ApiModelProperty(value = "是否只返回已审核通过的教练")
    private Boolean isApproved;
    
    @ApiModelProperty(value = "页码")
    private Integer page;
    
    @ApiModelProperty(value = "每页数量")
    private Integer limit;
}