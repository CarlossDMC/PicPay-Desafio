package com.carlos.picpaydesafio.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PagamentoDTO {
    private Long id_pagador;
    private Long id_recebedor;
    private Double valor;
}
