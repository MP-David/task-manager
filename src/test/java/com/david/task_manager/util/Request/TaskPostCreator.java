package com.david.task_manager.util.Request;

import com.david.task_manager.domain.ENUMS.PriorityEnum;
import com.david.task_manager.domain.ENUMS.StageEnum;
import com.david.task_manager.request.TaskPostRequestBody;

import java.time.LocalDate;

public class TaskPostCreator {

    public static TaskPostRequestBody createMockTaskPostRequestBody() {
        return TaskPostRequestBody.builder()
                .title("Tarefa de Teste")
                .description("Esta é uma descrição de teste para a tarefa")
                .stage(StageEnum.ON_GOING.name())
                .initDate(LocalDate.now())
                .endDate(LocalDate.now().plusDays(7))
                .priority(PriorityEnum.MEDIUM)
                .score(100L)
                .responsibleId(1L)
                .build();
    }
}
