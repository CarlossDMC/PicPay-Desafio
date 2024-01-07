package com.carlos.picpaydesafio.exceptions;

public class UsuarioNaoEncontradoException extends RuntimeException{
    public UsuarioNaoEncontradoException(String mensagem){
        super(mensagem);
    }

}
