// CampusUserPageVO.java
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
@ApiModel(description = "校区用户分页响应")
public class CampusUserPageVO implements Serializable {
    
    @ApiModelProperty(value = "用户列表")
    private List<CampusUserVO> users;
    
    @ApiModelProperty(value = "分页信息")
    private PaginationVO pagination;
}