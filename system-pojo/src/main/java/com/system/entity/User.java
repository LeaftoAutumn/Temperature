package com.system.entity;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    
    private static final long serialVersionUID = 1L;

    // 用户ID
    private UUID userId;

    // 用户名
    private String username;

    // 密码
    private String password;
    
    // 姓名
    private String name;
    
    // 性别
    private Gender gender;
    
    // 出生日期
    private LocalDateTime birthday;

    // 电话号码
    private String phoneNumber;
    
    // 电子邮件
    private String email;
    
    // 所属校区ID
    private UUID campusId;
    
    // 角色
    private Role role;

    // 创建时间
    private String createTime;

    // 更新时间
    private String updateTime;

    // 是否删除
    private Boolean deleted;

    @Getter
    public enum Gender {
        MALE("M", "男"),
        FEMALE("F", "女");

        private final String gender;
        private final String description;

        Gender(String gender, String description) {
            this.gender = gender;
            this.description = description;
        }
    }
    
    @Getter
    public enum Role {
        SUPER_ADMIN("SUPER_ADMIN", "超级管理员"),
        CAMPUS_ADMIN("CAMPUS_ADMIN", "校区管理员"),
        STUDENT("STUDENT", "学生"),
        COACH("COACH", "教练");

        private final String role;
        private final String description;

        Role(String role, String description) {
            this.role = role;
            this.description = description;
        }
    }
}
