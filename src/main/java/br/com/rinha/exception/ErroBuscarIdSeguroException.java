package br.com.rinha.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ErroBuscarIdSeguroException extends RuntimeException {

    public ErroBuscarIdSeguroException() {
        super();
    }

    public ErroBuscarIdSeguroException(String mensagem) {
        super(mensagem);
    }

    public ErroBuscarIdSeguroException(String mensagem, Throwable couse) {
        super(mensagem, couse);
    }
}
