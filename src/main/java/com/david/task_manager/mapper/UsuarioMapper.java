package com.david.task_manager.mapper;

import com.david.task_manager.domain.ENUMS.StageEnum;
import com.david.task_manager.domain.Usuario;
import com.david.task_manager.dto.UsuarioDTO;
import com.david.task_manager.request.UsuarioPostRequestBody;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", uses = {RoleMapper.class})
public abstract class UsuarioMapper {

    public abstract Usuario toUsuario(UsuarioPostRequestBody usuarioPostRequestBody);

    protected StageEnum mapStringToStageEnum(String stage) {
        if (stage == null) {
            return null;
        }
        return StageEnum.forValue(stage.toUpperCase());
    }

    protected UsuarioDTO toUsuarioLimitadoDTO(Usuario usuario) {
        if (usuario == null) {
            return null;
        }
        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(usuario.getId());
        dto.setName(usuario.getName());
        dto.setUsername(usuario.getUsername());
        return dto;
    }

    public List<UsuarioDTO> toUsuario(List<Usuario> usuarios) {
        return usuarios.stream().map(this::toUsuarioLimitadoDTO).toList();
    }
}
