// CampusCreateRequestDTO.java
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
@ApiModel(description = "创建校区请求参数")
public class CampusCreateRequestDTO implements Serializable {
    
    @ApiModelProperty(value = "校区名称", required = true, example = "上海分校区")
    private String name;
    
    @ApiModelProperty(value = "校区地址", example = "上海市浦东新区张江高科技园区")
    private String address;
    
    @ApiModelProperty(value = "联系电话", example = "021-87654321")
    private String contactPhone;
    
    @ApiModelProperty(value = "是否为中心校区", example = "false")
    private Boolean isCenter;
}