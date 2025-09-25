// RelatedInfoVO.java
package com.system.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "关联业务信息")
public class RelatedInfoVO implements Serializable {
    
    @ApiModelProperty(value = "业务类型")
    private String type;
    
    @ApiModelProperty(value = "业务标题")
    private String title;
    
    @ApiModelProperty(value = "业务时间")
    private LocalDateTime time;
}