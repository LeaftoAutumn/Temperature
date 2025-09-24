package com.system.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(description = "校区查询返回的简略数据格式")
public class BriefCampusVO implements Serializable {

    @ApiModelProperty("校区ID")
    private String campusId;

    @ApiModelProperty("校区名称")
    private String name;

    @ApiModelProperty("是否为中心校区")
    private Boolean center;
}
