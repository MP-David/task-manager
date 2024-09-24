package com.david.task_manager.request;

import com.david.task_manager.domain.ENUMS.RoleEnum;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class UsuarioPostRequestBody {

    @NotEmpty(message = "O nome é obrigatório")
    private String name;
    @NotEmpty(message = "O username é obrigatório")
    private String username;
    @NotEmpty(message = "A senha é obrigatória")
    private String password;
    private String email;
    @NotEmpty(message = "A função é obrigatória")
    private Set<RoleEnum> roles;
}
