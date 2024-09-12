package com.david.task_manager.mapper;

import com.david.task_manager.domain.Task;
import com.david.task_manager.domain.Usuario;
import com.david.task_manager.dto.TaskDTO;
import com.david.task_manager.request.TaskPostRequestBody;
import com.david.task_manager.request.TaskPutRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {UsuarioMapper.class})
public interface TaskMapper{


    @Mapping(target = "initDate", expression = "java(java.time.LocalDate.now())")
    @Mapping(target = "responsible", source = "responsibleId")
    Task toTask(TaskPostRequestBody taskPostRequestBody);

    @Mapping(target = "responsible", source = "responsibleId")
    Task toTask(TaskPutRequestBody taskPutRequestBody);

    @Mapping(target = "responsible", source = "responsible")
    TaskDTO toTaskDTO(Task task);

    List<TaskDTO> toTaskDTOList(List<Task> tasks);

    default Usuario mapIdToUsuario(Long id) {
        if (id == null) {
            return null;
        }
        Usuario usuario = new Usuario();
        usuario.setId(id);
        return usuario;
    }


}
