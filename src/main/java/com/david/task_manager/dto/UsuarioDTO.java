package com.david.task_manager.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UsuarioDTO {
    private Long id;
    private String name;
    private String username;

    // getters e setters
}