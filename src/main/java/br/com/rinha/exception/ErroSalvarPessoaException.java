package br.com.rinha.exception;

public class ErroSalvarPessoaException extends RuntimeException {
    public ErroSalvarPessoaException(){
        super();
    }
    public ErroSalvarPessoaException(String mensagem){
        super(mensagem);
    }
    public ErroSalvarPessoaException(String mensagem, Throwable couse){
        super(mensagem, couse);
    }
}
