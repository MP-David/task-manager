package com.david.task_manager.service;

import com.david.task_manager.domain.Usuario;
import com.david.task_manager.exception.BadRequest;
import com.david.task_manager.mapper.UsuarioMapper;
import com.david.task_manager.repository.UsuarioRepository;
import com.david.task_manager.request.UsuarioPostRequestBody;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;
    private final PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = findByUsername(username);
        if(usuario == null) throw new BadRequest("User não encontrado!");

        return User.withUsername(usuario.getUsername())
                .password(usuario.getPassword())
                .authorities(usuario.getRole().toString())
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }

    @Transactional
    public Usuario save(UsuarioPostRequestBody usuarioPostRequestBody) {
        if (findByUsername(usuarioPostRequestBody.getUsername()) != null) {
            throw new BadRequest("User já cadastrado!");
        }
        usuarioPostRequestBody.setPassword(passwordEncoder.encode(usuarioPostRequestBody.getPassword()));
        return usuarioRepository.save(usuarioMapper.toUsuario(usuarioPostRequestBody));
    }

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public Usuario findById(long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new BadRequest("Usuário não encontrado"));
    }

    public Usuario findByUsername(String username) {
        return usuarioRepository.findByUsername(username);
    }

}
