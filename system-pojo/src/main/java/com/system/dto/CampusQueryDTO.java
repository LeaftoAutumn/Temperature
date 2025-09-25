// CampusQueryDTO.java
package com.system.dto;

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
@ApiModel(description = "校区查询参数")
public class CampusQueryDTO implements Serializable {
    
    @ApiModelProperty(value = "角色过滤", example = "student")
    private String role;
    
    @ApiModelProperty(value = "页码", example = "1")
    private Integer page;
    
    @ApiModelProperty(value = "每页数量", example = "20")
    private Integer limit;
}