// Coach.java
package com.system.entity;

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
public class Coach implements Serializable {
    private static final long serialVersionUID = 1L;

    private UUID userId;
    private String level;
    private BigDecimal hourlyRate;
    private String photoUrl;
    private String awards;
    private Integer maxStudents;
    private Boolean isApproved;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Boolean deleted;
}