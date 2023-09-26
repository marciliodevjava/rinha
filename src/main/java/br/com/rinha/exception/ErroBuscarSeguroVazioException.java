package br.com.rinha.exception;

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
