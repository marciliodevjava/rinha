package br.com.rinha.enuns;

public enum MensagemEnum {
    ERRO_CPF_DUPLICADO_EXCEPTION("Esse CPF já constra cadastrado"),
    ERRO_METODO_INVALIDO_EXCEPTION("Requisição inválida do tipo: "),
    ERRO_CORPO_SOLICITACAO_AUXENTE_EXCEPTION("Corpo da solicitação necessário está ausente"),
    ERRO_BUSCAR_ID_SEGUROS_EXCEPTION("Campo id e seguros sem nenhum parametro valido");

    private final String mensagem;

    MensagemEnum(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }
}
