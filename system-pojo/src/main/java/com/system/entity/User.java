// User.java
package com.system.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private UUID id;
    private String username;
    private String passwordHash;
    private String name;
    private String gender;
    private LocalDate birthDate;
    private String phone;
    private String email;
    private UUID campusId;
    private String role;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Boolean deleted;
}