package br.com.rinha.enuns;

public enum MensagemEnum {

    ERRO_CPF_DUPLICADO_EXCEPTION("Esse CPF já constra cadastrado."),
    ERRO_METODO_INVALIDO_EXCEPTION("Requisição inválida do tipo: "),
    ERRO_CORPO_SOLICITACAO_AUXENTE_EXCEPTION("Corpo da solicitação necessário está ausente."),
    ERRO_BUSCAR_ID_SEGUROS_EXCEPTION("Campo id e seguros sem nenhum parametro valido."),
    ERRO_ID_NAO_EXCEPTION("Sem dados nessa consulta."),
    ERRO_UUID_IVALIDO_EXCEPTION("O id da solicitação não é um id válido."),
    ERRO_SEGURO_IVALIDO_EXCEPTION("Informar t é obrigatório."),
    ERRO_TERMO_IVALIDO_EXCEPTION("Informar t é obrigatório."),
    ERRO_TERMO_DATA_INVALIDO_EXCEPTION("Data invalida."),
    ERRO_TERMO_CONEXAO_EXCEPTION("Falha de Connect"),
    ERRO_TERMO_IO_EXCEPTION("Erro generico");

    private final String mensagem;

    MensagemEnum(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }
}