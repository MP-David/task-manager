package com.david.task_manager.controller;

import com.david.task_manager.config.JwtTokenUtil;
import com.david.task_manager.dto.JwtDto;
import com.david.task_manager.request.JwtPostRequestBody;
import com.david.task_manager.service.JwtService;
import com.david.task_manager.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping(path = "auth")
public class JwtAuthenticationController {

    private final JwtService jwtService;
    private final JwtTokenUtil jwtTokenUtil;
    private final UsuarioService usuarioService;

    @PostMapping()
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtPostRequestBody jwtPostRequestBody) throws Exception {
        try {
            jwtService.authenticate(jwtPostRequestBody.username(), jwtPostRequestBody.password());
            final UserDetails userDetails = usuarioService.loadUserByUsername(jwtPostRequestBody.password());
            final String token = jwtTokenUtil.generateToken(userDetails);
            return ResponseEntity.ok(new JwtDto(token));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return ResponseEntity.ok("Consute os logs");
    }

}