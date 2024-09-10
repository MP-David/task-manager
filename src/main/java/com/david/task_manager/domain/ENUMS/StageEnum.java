package com.david.task_manager.domain.ENUMS;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum StageEnum {
    BACKLOG(0), ON_GOING(1), DONE(2);

    private int codigo;

    StageEnum(int codigo) {
        this.codigo = codigo;
    }

    @JsonCreator
    public static StageEnum forValue(int value) {
        for (StageEnum stageEnum : StageEnum.values()) {
            if (stageEnum.codigo == value) {
                return stageEnum;
            }
        }
        return null;
    }

    public int getCodigo() {
        return codigo;
    }

    public static int getMaxCodigo() {
        int max = Integer.MIN_VALUE;
        for (StageEnum stage : values()) {
            if (stage.getCodigo() > max) {
                max = stage.getCodigo();
            }
        }
        return max;
    }

    public static int getMinCodigo() {
        int min = Integer.MAX_VALUE;
        for (StageEnum stage : values()) {
            if (stage.getCodigo() < min) {
                min = stage.getCodigo();
            }
        }
        return min;
    }
}