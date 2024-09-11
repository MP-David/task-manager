package com.david.task_manager.dto;

import com.david.task_manager.domain.ENUMS.PriorityEnum;
import com.david.task_manager.domain.ENUMS.StageEnum;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TaskDTO {
    private Long id;
    private String title;
    private String description;
    private LocalDate initDate;
    private LocalDate endDate;
    private Long score;
    private UsuarioLimitadoDTO responsible;
    private PriorityEnum priority;
    private StageEnum stage;

    // getters e setters
}