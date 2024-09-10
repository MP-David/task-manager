package com.david.task_manager.controller;

import com.david.task_manager.domain.Usuario;
import com.david.task_manager.request.UsuarioPostRequestBody;
import com.david.task_manager.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RequestMapping(path = "usuario")
@RestController
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping(path = "/creator")
    public ResponseEntity<Usuario> save(@RequestBody UsuarioPostRequestBody usuarioPostRequestBody) {
        return new ResponseEntity<>(usuarioService.save(usuarioPostRequestBody), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> findAll() {
        return ResponseEntity.ok(usuarioService.findAll());
    }
}


