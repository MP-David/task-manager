package com.david.task_manager.util;

import com.david.task_manager.domain.ENUMS.PriorityEnum;
import com.david.task_manager.domain.ENUMS.StageEnum;
import com.david.task_manager.dto.TaskDTO;
import com.david.task_manager.dto.UsuarioLimitadoDTO;

import java.time.LocalDate;
import java.util.List;

public class TaskCreator {

    public static List<TaskDTO> createTaskDTOList() {
        return List.of(createValidTaskDTO());
    }

    public static TaskDTO createValidTaskDTO() {
        TaskDTO task = new TaskDTO();
        task.setId(1L);
        task.setTitle("Tarefa de Teste");
        task.setDescription("Esta é uma descrição de teste para a tarefa mock");
        task.setInitDate(LocalDate.now());
        task.setEndDate(LocalDate.now().plusDays(7));
        task.setScore(100L);

        UsuarioLimitadoDTO responsavel = new UsuarioLimitadoDTO();
        responsavel.setId(1L);
        responsavel.setName("Usuário Teste");
        task.setResponsible(responsavel);

        task.setPriority(PriorityEnum.MEDIUM);
        task.setStage(StageEnum.ON_GOING);

        return task;
    }




}
