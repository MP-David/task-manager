package com.david.task_manager.domain.ENUMS;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum PriorityEnum {
    LOW(1),
    MEDIUM(2),
    HIGH(3);

    private int codigo;

    PriorityEnum(int codigo) {
        this.codigo = codigo;
    }

    @JsonValue
    public String getValue() {
        return this.name();
    }

    @JsonCreator
    public static StageEnum forValue(String value) {
        for (StageEnum stageEnum : StageEnum.values()) {
            if (stageEnum.name().equalsIgnoreCase(value)) {
                return stageEnum;
            }
        }
        throw new IllegalArgumentException("Invalid StageEnum value: " + value);
    }

}