package com.david.task_manager.request;

import com.david.task_manager.domain.ENUMS.RoleEnum;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

import java.util.Set;

@Builder
public record UsuarioPostRequestBody (
        @NotEmpty String name,
        @NotEmpty String username,
        @NotEmpty String password,
        String email,
        @NotEmpty Set<RoleEnum> roles
) {


}
