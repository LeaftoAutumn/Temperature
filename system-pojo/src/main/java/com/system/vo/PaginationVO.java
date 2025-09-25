// PaginationVO.java
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
@ApiModel(description = "分页信息")
public class PaginationVO implements Serializable {
    
    @ApiModelProperty(value = "当前页码")
    private Integer page;
    
    @ApiModelProperty(value = "每页数量")
    private Integer limit;
    
    @ApiModelProperty(value = "总记录数")
    private Long total;
    
    @ApiModelProperty(value = "总页数")
    private Integer pages;
}