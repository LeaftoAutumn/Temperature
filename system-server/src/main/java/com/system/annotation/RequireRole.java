package com.system.annotation;
// RequireRole.java
import com.system.enumeration.UserRole;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RequireRole {
    UserRole[] value() default {};

    Logic logic() default Logic.OR;

    enum Logic {
        AND, OR
    }
}