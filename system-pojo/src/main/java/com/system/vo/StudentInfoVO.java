// StudentInfoVO.java
package com.system.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "学员扩展信息")
public class StudentInfoVO implements Serializable {
    
    @ApiModelProperty(value = "学员ID")
    private UUID id;
    
    @ApiModelProperty(value = "账户余额")
    private BigDecimal balance;
    
    @ApiModelProperty(value = "最多教练数量")
    private Integer maxCoaches;
    
    @ApiModelProperty(value = "取消课程次数")
    private Integer cancelCount;
    
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;
}