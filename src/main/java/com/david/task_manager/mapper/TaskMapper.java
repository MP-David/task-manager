package com.david.task_manager.mapper;

import com.david.task_manager.domain.ENUMS.StageEnum;
import com.david.task_manager.domain.Task;
import com.david.task_manager.domain.Usuario;
import com.david.task_manager.dto.TaskDTO;
import com.david.task_manager.dto.UsuarioLimitadoDTO;
import com.david.task_manager.request.TaskPostRequestBody;
import com.david.task_manager.request.TaskPutRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
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

    default StageEnum mapStringToStageEnum(String stage) {
        if (stage == null) {
            return null;
        }
        return StageEnum.forValue(stage.toUpperCase());
    }

    default UsuarioLimitadoDTO toUsuarioLimitadoDTO(Usuario usuario) {
        if (usuario == null) {
            return null;
        }
        UsuarioLimitadoDTO dto = new UsuarioLimitadoDTO();
        dto.setId(usuario.getId());
        dto.setName(usuario.getName());
        dto.setUsername(usuario.getUsername());
        return dto;
    }
}
