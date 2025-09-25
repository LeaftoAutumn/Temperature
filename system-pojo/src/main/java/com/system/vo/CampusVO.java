// CampusVO.java
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
@ApiModel(description = "校区信息")
public class CampusVO implements Serializable {
    
    @ApiModelProperty(value = "校区ID")
    private UUID id;
    
    @ApiModelProperty(value = "校区名称")
    private String name;
    
    @ApiModelProperty(value = "校区地址")
    private String address;
    
    @ApiModelProperty(value = "联系电话")
    private String contactPhone;
    
    @ApiModelProperty(value = "是否为中心校区")
    private Boolean isCenter;
    
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;
}