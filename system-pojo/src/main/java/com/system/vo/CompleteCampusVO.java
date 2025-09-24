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
@ApiModel(description = "校区查询返回的完整数据格式")
public class CompleteCampusVO implements Serializable {

    @ApiModelProperty("校区ID")
    private String campusId;

    @ApiModelProperty("校区名称")
    private String name;

    @ApiModelProperty("校区地址")
    private String address;

    @ApiModelProperty("校区联系电话")
    private String phone;

    @ApiModelProperty("是否为中心校区")
    private Boolean center;
}
