package br.com.rinha.exception;

public class ErroBuscarIdSeguroException extends RuntimeException {
    public ErroBuscarIdSeguroException(){
        super();
    }
    public ErroBuscarIdSeguroException(String mensagem){
        super(mensagem);
    }

    public ErroBuscarIdSeguroException(String mensagem, Throwable couse){
        super(mensagem, couse);
    }
}
