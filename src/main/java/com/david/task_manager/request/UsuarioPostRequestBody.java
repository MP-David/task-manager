package com.david.task_manager.request;

import com.david.task_manager.domain.ENUMS.RoleEnum;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UsuarioPostRequestBody {

    @NotEmpty(message = "O nome é obrigatório")
    String name;
    @NotEmpty(message = "O username é obrigatório")
    String username;
    @NotEmpty(message = "A senha é obrigatória")
    String password;
    @NotEmpty(message = "A função é obrigatória")
    RoleEnum role;
}
