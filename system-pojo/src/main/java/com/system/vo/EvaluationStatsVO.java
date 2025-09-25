// EvaluationStatsVO.java
package com.system.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "评价统计信息")
public class EvaluationStatsVO implements Serializable {
    
    @ApiModelProperty(value = "收到的评价总数")
    private Integer totalReceived;
    
    @ApiModelProperty(value = "平均评分")
    private Double averageRating;
    
    @ApiModelProperty(value = "评分分布")
    private Map<Integer, Integer> ratingDistribution;
    
    @ApiModelProperty(value = "最近评价时间")
    private LocalDateTime lastEvaluationTime;
}