// Tournament.java
package com.system.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Tournament implements Serializable {
    private static final long serialVersionUID = 1L;

    private UUID id;
    private String name;
    private LocalDate eventDate;
    private String groupType; // A, B, C
    private String format; // round_robin, knockout
    private String status; // upcoming, ongoing, completed
    private BigDecimal entryFee;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Boolean deleted;
}