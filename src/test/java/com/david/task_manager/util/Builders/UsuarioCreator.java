package com.david.task_manager.util.Builders;

import com.david.task_manager.domain.ENUMS.RoleEnum;
import com.david.task_manager.domain.Role;
import com.david.task_manager.domain.Task;
import com.david.task_manager.domain.Usuario;
import com.david.task_manager.dto.UsuarioDTO;

import java.util.HashSet;
import java.util.Set;

public class UsuarioCreator {

    public static UsuarioDTO createUsuarioDTO() {
        return UsuarioDTO.builder()
                .id(1L)
                .name("Usuário Teste")
                .username("testeUser").build();
    }

    public static Usuario createUsuario() {
        Usuario usuario = new Usuario();

        // Set basic properties
        usuario.setId(1L);
        usuario.setName("João Silva");
        usuario.setUsername("joao.silva");
        usuario.setPassword("senha123");
        usuario.setEmail("joao.silva@example.com");

        // Create and set tasks
        Set<Task> tasks = new HashSet<>();
        Task task1 = new Task();
        task1.setId(1L);
        task1.setTitle("Implement user authentication");
        task1.setResponsible(usuario);
        tasks.add(task1);
        usuario.setTasks(tasks);

        // Create and set roles
        Set<Role> roles = new HashSet<>();
        Role userRole = new Role();
        userRole.setId(1L);
        userRole.setName(RoleEnum.ROLE_USER);
        roles.add(userRole);
        usuario.setRoles(roles);

        return usuario;
    }

}