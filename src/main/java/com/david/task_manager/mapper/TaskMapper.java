package com.david.task_manager.mapper;

import com.david.task_manager.domain.Task;
import com.david.task_manager.domain.Usuario;
import com.david.task_manager.dto.TaskDTO;
import com.david.task_manager.request.TaskPostRequestBody;
import com.david.task_manager.request.TaskPutRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring", uses = {UsuarioMapper.class})
public abstract class TaskMapper{


    @Mapping(target = "initDate", expression = "java(java.time.LocalDate.now())")
    @Mapping(target = "responsible", source = "responsibleId")
    public abstract Task toTask(TaskPostRequestBody taskPostRequestBody);

    @Mapping(target = "responsible", source = "responsibleId")
    public abstract Task toTask(TaskPutRequestBody taskPutRequestBody);

    @Mapping(target = "responsible", source = "responsible")
    public abstract TaskDTO toTaskDTO(Task task);

    public abstract List<TaskDTO> toTaskDTOList(List<Task> tasks);

    public Page<TaskDTO> toTaskDTOPage(Page<Task> tasks) {
        return tasks.map(this::toTaskDTO);
    }

    Usuario mapIdToUsuario(Long id) {
        if (id == null) {
            return null;
        }
        Usuario usuario = new Usuario();
        usuario.setId(id);
        return usuario;
    }


}
