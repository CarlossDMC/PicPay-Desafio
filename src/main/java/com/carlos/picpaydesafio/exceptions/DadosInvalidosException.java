package com.carlos.picpaydesafio.exceptions;

public class DadosInvalidosException extends RuntimeException{
    public DadosInvalidosException(String mensagem){
        super(mensagem);
    }
}
