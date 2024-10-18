package com.david.task_manager.controller;

import com.david.task_manager.domain.Usuario;
import com.david.task_manager.dto.UsuarioDTO;
import com.david.task_manager.mapper.UsuarioMapper;
import com.david.task_manager.request.UsuarioPostRequestBody;
import com.david.task_manager.service.UsuarioService;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.assertj.core.api.Assertions;

import java.util.List;

import static com.david.task_manager.util.Builders.UsuarioCreator.createUsuario;
import static com.david.task_manager.util.Builders.UsuarioCreator.createUsuarioDTO;
import static com.david.task_manager.util.Request.UsuarioPostCreator.createMockUsuarioPostRequestBody;

@ExtendWith(SpringExtension.class)
class UsuarioControllerTest {

    @InjectMocks
    private UsuarioController usuarioController;

    @Mock
    private UsuarioService usuarioServiceMock;

    @BeforeEach
    void setUp() {

        BDDMockito.when(usuarioServiceMock.findAll())
                .thenReturn(List.of(createUsuarioDTO()));

        BDDMockito.when(usuarioServiceMock.save(ArgumentMatchers.any(UsuarioPostRequestBody.class)))
                .thenReturn(createUsuario());

    }

    @Test
    void save() {
        ResponseEntity<Usuario> response = usuarioController.save(createMockUsuarioPostRequestBody());

        Assertions.assertThat(response)
                .isNotNull()
                .extracting(ResponseEntity::getStatusCode)
                .isEqualTo(HttpStatus.CREATED);

        Assertions.assertThat(response.getBody())
                .isNotNull()
                .isEqualTo(createUsuario());

        Assertions.assertThat(response.getBody().getName())
                .isEqualTo(createUsuario().getName());
    }

    @Test
    void findAll() {

        ResponseEntity<List<UsuarioDTO>> all = usuarioController.findAll();

        Assertions.assertThat(all)
                .isNotNull()
                .extracting(ResponseEntity::getStatusCode)
                .isEqualTo(HttpStatus.OK);

        Assertions.assertThat(all.getBody())
                .isNotNull()
                .hasSize(1);

        Assertions.assertThat(all.getBody().get(0))
                .isNotNull()
                .isEqualTo(createUsuarioDTO());
    }
}