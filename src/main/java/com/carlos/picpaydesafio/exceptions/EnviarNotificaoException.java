package com.carlos.picpaydesafio.exceptions;

public class EnviarNotificaoException extends RuntimeException{
    public EnviarNotificaoException(String mensagem){
        super(mensagem);
    }
}
