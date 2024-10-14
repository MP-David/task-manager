package com.david.task_manager.service;

import com.david.task_manager.domain.Usuario;
import com.david.task_manager.dto.UsuarioDTO;
import com.david.task_manager.exception.UserNotFoundException;
import com.david.task_manager.mapper.RoleMapper;
import com.david.task_manager.mapper.UsuarioMapper;
import com.david.task_manager.repository.RoleRepository;
import com.david.task_manager.repository.UsuarioRepository;
import com.david.task_manager.request.UsuarioPostRequestBody;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = findByUsername(username);
        if(usuario == null) throw new UserNotFoundException("User não encontrado!");


        Set<SimpleGrantedAuthority> authorities = roleRepository.findRolesByUserId(usuario.getId()).stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                .collect(Collectors.toSet());

        return User.withUsername(usuario.getUsername())
                .password(usuario.getPassword())
                .authorities(authorities)
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }

    @Transactional
    public Usuario save(UsuarioPostRequestBody usuarioPostRequestBody) {
        if (findByUsername(usuarioPostRequestBody.username()) != null) {
            throw new UserNotFoundException("User já cadastrado!");
        }

         return usuarioRepository.save(usuarioMapper.toUsuario(usuarioPostRequestBody, passwordEncoder));
    }

    public List<UsuarioDTO> findAll() {
        List<Usuario> all = usuarioRepository.findAll();
        return usuarioMapper.toUsuarioDto(all);
    }

    public Usuario findById(long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Usuário não encontrado"));
    }

    public Usuario findByUsername(String username) {
        return usuarioRepository.findByUsername(username);
    }

}
