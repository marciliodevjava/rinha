package br.com.rinha.dto.request;

import br.com.rinha.annotations.interfaces.CPFouCNPJ;
import br.com.rinha.annotations.interfaces.NaoNulo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class PessoaDto {
    @NaoNulo(message = "Campo nome não poder ser nulo, coloque um nome valido")
    private String nome;
    @NaoNulo(message = "Campo nome não poder ser nulo, coloque um nome valido")
    @CPFouCNPJ(message = "CPF ou CNPJ inválido, coloque um CPF ou CNPJ válido.")
    private String cpfCnpj;
    private String nacimento;
    private List<String> seguros;
}