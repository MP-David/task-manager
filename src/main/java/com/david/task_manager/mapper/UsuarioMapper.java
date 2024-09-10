package com.david.task_manager.mapper;

import com.david.task_manager.domain.Usuario;
import com.david.task_manager.request.UsuarioPostRequestBody;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    Usuario toUsuario(UsuarioPostRequestBody usuarioPostRequestBody);
}
