package br.com.rinha.rinha.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class PessoaDto {
    private String nome;
    private String cpfCnpj;
    private String nacimento;
    private List<String> seguros;
}
