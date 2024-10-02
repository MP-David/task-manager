package com.david.task_manager.util.Request;

import com.david.task_manager.domain.ENUMS.RoleEnum;
import com.david.task_manager.request.UsuarioPostRequestBody;

import java.util.Set;

public class UsuarioPostCreator {

    public static UsuarioPostRequestBody createMockUsuarioPostRequestBody() {
        return UsuarioPostRequestBody.builder()
                .name("Usuário Teste")
                .username("testeUser")
                .password("123456")
                .roles(Set.of(RoleEnum.ROLE_USER))
                .build();
    }
}
