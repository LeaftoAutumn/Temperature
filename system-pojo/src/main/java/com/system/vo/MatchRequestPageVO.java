// MatchRequestPageVO.java
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
@ApiModel(description = "匹配申请分页响应")
public class MatchRequestPageVO implements Serializable {
    
    @ApiModelProperty(value = "申请列表")
    private List<MatchRequestDetailVO> requests;
    
    @ApiModelProperty(value = "分页信息")
    private PaginationVO pagination;
}