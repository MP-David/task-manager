package com.david.task_manager.dto;

import lombok.Data;

@Data
public class JwtDto {

    private final String token;

    public JwtDto(String token) {
        this.token = token;
    }

}
