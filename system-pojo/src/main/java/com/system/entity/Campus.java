// Campus.java
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
public class Campus implements Serializable {
    private static final long serialVersionUID = 1L;

    private UUID campusId;
    private String name;
    private String address;
    private String phone;
    private Boolean center;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Boolean deleted;
}