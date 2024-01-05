package com.carlos.picpaydesafio.exceptions;



public class TipoInvalidoException extends RuntimeException {
    public TipoInvalidoException(String mensagem){
        super(mensagem);
    }
}
