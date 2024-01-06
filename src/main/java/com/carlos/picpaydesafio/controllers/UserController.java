package com.carlos.picpaydesafio.controllers;

import com.carlos.picpaydesafio.dto.UsuarioCriacaoDTO;
import com.carlos.picpaydesafio.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> create(@RequestBody UsuarioCriacaoDTO usuarioDTO){
        Map<String, Object> retorno = new HashMap<>();
            retorno = usuarioService.criarNovoUsuario(usuarioDTO.getUsuario(), usuarioDTO.getSaldo_inicial());
            return ResponseEntity.status(HttpStatus.CREATED).body(retorno);
    }
}


