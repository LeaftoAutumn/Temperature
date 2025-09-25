// MatchRequestVO.java
package com.system.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "匹配申请信息")
public class MatchRequestVO implements Serializable {
    
    @ApiModelProperty(value = "申请ID")
    private UUID id;
    
    @ApiModelProperty(value = "学员ID")
    private UUID studentId;
    
    @ApiModelProperty(value = "教练ID")
    private UUID coachId;
    
    @ApiModelProperty(value = "申请状态")
    private String status;
    
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdTime;
    
    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updatedTime;
}