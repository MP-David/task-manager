package com.david.task_manager.mapper;

import com.david.task_manager.domain.ENUMS.StageEnum;
import com.david.task_manager.domain.Usuario;
import com.david.task_manager.dto.UsuarioLimitadoDTO;
import com.david.task_manager.request.UsuarioPostRequestBody;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    Usuario toUsuario(UsuarioPostRequestBody usuarioPostRequestBody);

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
