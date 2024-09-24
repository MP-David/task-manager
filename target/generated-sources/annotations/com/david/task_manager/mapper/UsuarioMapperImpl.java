package com.david.task_manager.mapper;

import com.david.task_manager.domain.Usuario;
import com.david.task_manager.request.UsuarioPostRequestBody;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-24T09:47:15-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.10 (Oracle Corporation)"
)
@Component
public class UsuarioMapperImpl extends UsuarioMapper {

    @Override
    public Usuario toUsuario(UsuarioPostRequestBody usuarioPostRequestBody) {
        if ( usuarioPostRequestBody == null ) {
            return null;
        }

        Usuario usuario = new Usuario();

        usuario.setName( usuarioPostRequestBody.getName() );
        usuario.setUsername( usuarioPostRequestBody.getUsername() );
        usuario.setPassword( usuarioPostRequestBody.getPassword() );
        usuario.setEmail( usuarioPostRequestBody.getEmail() );

        usuario.setRoles( mapRoles(usuarioPostRequestBody.getRoles()) );

        return usuario;
    }
}
