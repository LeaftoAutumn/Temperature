// Evaluation.java
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
public class Evaluation implements Serializable {
    private static final long serialVersionUID = 1L;

    private UUID id;
    private UUID courseId;
    private UUID fromUserId;
    private UUID toUserId;
    private String content;
    private Integer rating;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Boolean deleted;
}