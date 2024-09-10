package com.david.task_manager.domain.ENUMS;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum PriorityEnum {
    LOW(1),
    MEDIUM(2),
    HIGH(3);

    private int codigo;

    PriorityEnum(int codigo) {
        this.codigo = codigo;
    }

    public String getName() {
        return this.name();
    }

    @JsonCreator
    public static PriorityEnum valueOf(int codigo) {
        for (PriorityEnum p : PriorityEnum.values()) {
            if (p.codigo == codigo) {
                return p;
            }
        }
        return null;
    }
}