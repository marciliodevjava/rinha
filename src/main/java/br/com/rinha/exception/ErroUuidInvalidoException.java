package br.com.rinha.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ErroUuidInvalidoException extends RuntimeException {

    public ErroUuidInvalidoException() {
        super();
    }

    public ErroUuidInvalidoException(String mensagem) {
        super(mensagem);
    }

    public ErroUuidInvalidoException(String mensagem, Throwable couse) {
        super(mensagem, couse);
    }
}
