// CoachChangeRequest.java
package com.system.entity;

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
public class CoachChangeRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    private UUID id;
    private UUID studentId;
    private UUID currentCoachId;
    private UUID newCoachId;
    private String reason;
    private String status; // pending, approved, rejected
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Boolean deleted;
}