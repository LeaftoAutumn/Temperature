// CoachPageVO.java
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
@ApiModel(description = "教练分页响应")
public class CoachPageVO implements Serializable {
    
    @ApiModelProperty(value = "教练列表")
    private List<CoachListItemVO> coaches;
    
    @ApiModelProperty(value = "分页信息")
    private PaginationVO pagination;
}