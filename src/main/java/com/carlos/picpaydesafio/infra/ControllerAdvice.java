package com.carlos.picpaydesafio.infra;
import com.carlos.picpaydesafio.exceptions.DadosInvalidosException;
import com.carlos.picpaydesafio.exceptions.TipoInvalidoException;
import com.carlos.picpaydesafio.exceptions.UsuarioNaoEncontradoException;
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
    private ResponseEntity<String> usuarioNaoEncontradoException(TipoInvalidoException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }
}
