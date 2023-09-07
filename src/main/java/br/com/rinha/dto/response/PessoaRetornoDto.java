package br.com.rinha.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class PessoaRetornoDto {
    private String id;
    private String nome;
    private String cpfCnpj;
    private String nacimento;
    private List<String> seguros;
}
