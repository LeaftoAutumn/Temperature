// CancellationRemainingVO.java
package com.system.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
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
@ApiModel(description = "剩余取消次数")
public class CancellationRemainingVO implements Serializable {
    
    @ApiModelProperty(value = "剩余次数")
    private Integer remaining;
    
    @ApiModelProperty(value = "已使用次数")
    private Integer used;
}