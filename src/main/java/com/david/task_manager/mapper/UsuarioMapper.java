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

    protected UsuarioDTO toUsuarioDTO(Usuario usuario) {
        if (usuario == null) {
            return null;
        }
        return new UsuarioDTO(usuario.getId(), usuario.getName(), usuario.getUsername());
    }

    public List<UsuarioDTO> toUsuarioDto(List<Usuario> usuarios) {
        return usuarios.stream().map(this::toUsuarioDTO).toList();
    }
}
