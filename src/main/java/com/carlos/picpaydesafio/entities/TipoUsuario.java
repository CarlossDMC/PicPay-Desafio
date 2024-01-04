package com.carlos.picpaydesafio.entities;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public enum TipoUsuario {
    @Enumerated(EnumType.STRING)
    USUARIO,
    @Enumerated(EnumType.STRING)
    LOJISTA
}
