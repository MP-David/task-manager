package com.david.task_manager.domain.ENUMS;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.ArrayList;
import java.util.List;

public enum StageEnum {

    BACKLOG(0),
    ON_GOING(1),
    DONE(2);

    private int codigo;

    StageEnum(int codigo) {
        this.codigo = codigo;
    }

    @JsonValue
    public String getName() {
        return this.name();
    }

    @JsonCreator
    public static StageEnum forValue(String value) {
        for (StageEnum stageEnum : StageEnum.values()) {
            if (stageEnum.name().equals(value)) {
                return stageEnum;
            }
        }
        return null;
    }

    public static List<String> validValues() {
        List<String> list = new ArrayList<>();
        for (StageEnum value : StageEnum.values()) {
            list.add(value.getName());
        }
        return list;
    }
}