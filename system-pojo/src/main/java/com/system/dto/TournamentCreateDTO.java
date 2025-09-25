// TournamentCreateDTO.java
package com.system.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "创建月赛请求参数")
public class TournamentCreateDTO implements Serializable {
    
    @ApiModelProperty(value = "月赛名称", required = true)
    private String name;
    
    @ApiModelProperty(value = "比赛日期", required = true)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate eventDate;
    
    @ApiModelProperty(value = "分组类型", required = true)
    private String groupType;
    
    @ApiModelProperty(value = "赛制", required = true)
    private String format;
    
    @ApiModelProperty(value = "报名费", required = true)
    private BigDecimal entryFee;
}