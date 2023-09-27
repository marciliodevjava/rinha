package br.com.rinha.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ErroBuscarSeguroVazioException extends RuntimeException {

    public ErroBuscarSeguroVazioException() {
        super();
    }

    public ErroBuscarSeguroVazioException(String mensagem) {
        super(mensagem);
    }

    public ErroBuscarSeguroVazioException(String mensagem, Throwable couse) {
        super(mensagem, couse);
    }
}