package com.david.task_manager.request;

import com.david.task_manager.domain.ENUMS.PriorityEnum;
import com.david.task_manager.domain.ENUMS.StageEnum;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
@Data
public class TaskPostRequestBody {

    @NotEmpty(message = "Titulo é obrigatório")
    String title;
    @NotEmpty(message = "Descrição é obrigatório")
    String description;
    @NotNull(message = "Stage é obrigatório")
    String stage;
    @Builder.Default
    LocalDate initDate = LocalDate.now();
    LocalDate endDate;
    PriorityEnum priority;
    Long score;
    Long responsibleId;

    public String getStage() {
        return stage;
    }



}
