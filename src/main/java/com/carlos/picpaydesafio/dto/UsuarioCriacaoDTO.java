package com.carlos.picpaydesafio.dto;

import com.carlos.picpaydesafio.entities.Usuario;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UsuarioCriacaoDTO {
    private Usuario usuario;
    private Double saldo_inicial;
}
