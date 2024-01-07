package com.carlos.picpaydesafio.infra;
import com.carlos.picpaydesafio.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice extends ResponseEntityExceptionHandler {
    @ExceptionHandler(DadosInvalidosException.class)
    private ResponseEntity<String> dadosInvalidosHandler(DadosInvalidosException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }

    @ExceptionHandler(TipoInvalidoException.class)
    private ResponseEntity<String> tipoInvalidoHandler(TipoInvalidoException exception){
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(exception.getMessage());
    }

    @ExceptionHandler(UsuarioNaoEncontradoException.class)
    private ResponseEntity<String> usuarioNaoEncontradoException(UsuarioNaoEncontradoException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ExceptionHandler(SaldoInsuficienteException.class)
    private ResponseEntity<String> saldoInsuficienteException(SaldoInsuficienteException exception){
        return ResponseEntity.status(HttpStatus.PAYMENT_REQUIRED).body(exception.getMessage());
    }

    @ExceptionHandler(OperacaoNaoPermitidaException.class)
    private ResponseEntity<String> operacaoNaoPermitidaException(OperacaoNaoPermitidaException exception){
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(exception.getMessage());
    }

    @ExceptionHandler(EnviarNotificaoException.class)
    private ResponseEntity<String> enviarNotificaoException(EnviarNotificaoException exception){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
    }
}
