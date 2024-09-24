package com.david.task_manager.domain.ENUMS;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum RoleEnum {
    ROLE_ADMIN(1),
    ROLE_USER(2),
    ROLE_DEVELOPER(3);

    private int value;

    RoleEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @JsonValue
    public String getName() {
        return name();
    }

    @JsonCreator
    public static RoleEnum fromString(String value) {
        for (RoleEnum roleEnum : RoleEnum.values()) {
            if (roleEnum.name().equalsIgnoreCase(value)) {
                return roleEnum;
            }
        }
        throw new IllegalArgumentException("Invalid RoleEnum value: " + value);
    }

    public static RoleEnum fromInt(int value) {
        for (RoleEnum roleEnum : RoleEnum.values()) {
            if (roleEnum.value == value) {
                return roleEnum;
            }
        }
        throw new IllegalArgumentException("Invalid RoleEnum value: " + value);
    }
}