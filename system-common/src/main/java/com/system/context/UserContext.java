package com.system.context;

import com.system.enumeration.UserRole;

import java.util.UUID;

// UserContext.java
public class UserContext {
    private static final ThreadLocal<UUID> USER_ID = new ThreadLocal<>();
    private static final ThreadLocal<UserRole> USER_ROLE = new ThreadLocal<>();
    
    public static void setUserId(UUID userId) {
        USER_ID.set(userId);
    }
    
    public static UUID getUserId() {
        return USER_ID.get();
    }
    
    public static void setUserRole(UserRole role) {
        USER_ROLE.set(role);
    }
    
    public static UserRole getUserRole() {
        return USER_ROLE.get();
    }
    
    public static void clear() {
        USER_ID.remove();
        USER_ROLE.remove();
    }
}