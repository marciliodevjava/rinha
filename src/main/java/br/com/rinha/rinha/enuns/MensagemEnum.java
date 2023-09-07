package br.com.rinha.rinha.enuns;

public enum MensagemEnum {
    ERRO_CPF_DUPLICADO_EXCEPTION("Esse CPF já constra cadastrado");

    private final String mensagem;
    MensagemEnum(String mensagem){
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }
}
