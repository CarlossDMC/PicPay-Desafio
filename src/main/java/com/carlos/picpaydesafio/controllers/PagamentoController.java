package com.carlos.picpaydesafio.controllers;

import com.carlos.picpaydesafio.dto.PagamentoDTO;
import com.carlos.picpaydesafio.dto.UsuarioCriacaoDTO;
import com.carlos.picpaydesafio.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/transaction")
public class PagamentoController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping()
    public ResponseEntity<String> transaction(@RequestBody PagamentoDTO pagamentoDTO){
        usuarioService.enviarDinhero(pagamentoDTO.getValor(), pagamentoDTO.getId_pagador(), pagamentoDTO.getId_recebedor());
        return ResponseEntity.status(HttpStatus.OK).body("Enviado com sucesso!");
    }
}
