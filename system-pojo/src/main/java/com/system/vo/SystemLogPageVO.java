// SystemLogPageVO.java
package com.system.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "系统日志分页响应")
public class SystemLogPageVO implements Serializable {
    
    @ApiModelProperty(value = "日志列表")
    private List<SystemLogVO> data;
    
    @ApiModelProperty(value = "分页信息")
    private PaginationVO pagination;
}