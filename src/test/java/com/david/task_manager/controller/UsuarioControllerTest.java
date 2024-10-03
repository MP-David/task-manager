package com.david.task_manager.controller;

import com.david.task_manager.request.UsuarioPostRequestBody;
import com.david.task_manager.service.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static com.david.task_manager.util.Builders.UsuarioCreator.createUsuario;
import static com.david.task_manager.util.Builders.UsuarioCreator.createUsuarioDTO;
import static com.david.task_manager.util.Request.UsuarioPostCreator.createMockUsuarioPostRequestBody;
import static org.junit.jupiter.api.Assertions.*;

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
    }

    @Test
    void findAll() {
    }
}