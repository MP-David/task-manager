package com.david.task_manager.dto;

import com.david.task_manager.domain.ENUMS.PriorityEnum;
import com.david.task_manager.domain.ENUMS.StageEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskDTO {
    private Long id;
    private String title;
    private String description;
    private LocalDate initDate;
    private LocalDate endDate;
    private Long score;
    private UsuarioDTO responsible;
    private PriorityEnum priority;
    private StageEnum stage;

}