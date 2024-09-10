package com.david.task_manager.domain.ENUMS;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum RoleEnum {
    ROLE_ADMIN(0);

    private int value;

    RoleEnum(int value) {
        this.value = value;
    }

    @JsonCreator
    public static RoleEnum fromInt(int value) {
        for (RoleEnum roleEnum : RoleEnum.values()) {
            if (roleEnum.value == value) {
                return roleEnum;
            }
        }
        return null;
    }
}