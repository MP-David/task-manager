package com.david.task_manager.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UsuarioDTO {
    private Long id;
    private String name;
    private String username;

    public UsuarioDTO(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.username = email;
    }
}