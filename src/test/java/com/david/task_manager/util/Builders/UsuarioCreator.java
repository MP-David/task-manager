package com.david.task_manager.util.Builders;

import com.david.task_manager.dto.UsuarioDTO;

public class UsuarioCreator {

    public static UsuarioDTO createUsuarioDTO() {
        return UsuarioDTO.builder()
                .id(1L)
                .name("Usuário Teste")
                .username("testeUser").build();
    }

}