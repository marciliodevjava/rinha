package br.com.rinha.dto.request;

import br.com.rinha.annotations.interfaces.CPFouCNPJ;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class PessoaDto {
    @NotBlank
    private String nome;
    @NotBlank
    @CPFouCNPJ(message = "CPF ou CNPJ inválido, coloque um CPF ou CNPJ válido.")
    private String cpfCnpj;
    private String nascimento;
    private List<String> seguros;
}