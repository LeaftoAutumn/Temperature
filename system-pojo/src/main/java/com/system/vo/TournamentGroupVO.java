// TournamentGroupVO.java
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
@ApiModel(description = "月赛分组信息")
public class TournamentGroupVO implements Serializable {
    
    @ApiModelProperty(value = "组别名称")
    private String groupName;
    
    @ApiModelProperty(value = "选手列表")
    private List<PlayerInfoVO> players;
    
    @ApiModelProperty(value = "比赛安排")
    private List<TournamentMatchVO> matches;
}