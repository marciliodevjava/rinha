package br.com.rinha.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ErroSalvarPessoaException extends RuntimeException {

    public ErroSalvarPessoaException() {
        super();
    }

    public ErroSalvarPessoaException(String mensagem) {
        super(mensagem);
    }

    public ErroSalvarPessoaException(String mensagem, Throwable couse) {
        super(mensagem, couse);
    }
}
