package com.carlos.picpaydesafio.exceptions;

public class OperacaoNaoPermitidaException extends RuntimeException{
    public OperacaoNaoPermitidaException(String message){
        super(message);
    }
}
