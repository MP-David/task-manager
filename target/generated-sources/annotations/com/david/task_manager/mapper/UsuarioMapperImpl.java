package com.david.task_manager.mapper;

import com.david.task_manager.domain.Usuario;
import com.david.task_manager.request.UsuarioPostRequestBody;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-18T16:03:36-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.10 (Oracle Corporation)"
)
@Component
public class UsuarioMapperImpl extends UsuarioMapper {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public Usuario toUsuario(UsuarioPostRequestBody usuarioPostRequestBody, PasswordEncoder passwordEncoder) {
        if ( usuarioPostRequestBody == null && passwordEncoder == null ) {
            return null;
        }

        Usuario usuario = new Usuario();

        if ( usuarioPostRequestBody != null ) {
            usuario.setName( usuarioPostRequestBody.name() );
            usuario.setUsername( usuarioPostRequestBody.username() );
            usuario.setEmail( usuarioPostRequestBody.email() );
            usuario.setRoles( roleMapper.mapRoles( usuarioPostRequestBody.roles() ) );
        }
        usuario.setPassword( passwordEncoder.encode(usuarioPostRequestBody.password()) );

        return usuario;
    }
}
