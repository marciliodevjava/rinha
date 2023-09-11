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
    @NotBlank(message = "Campo nome não poder ser nulo")
    private String nome;
    @NotBlank(message = "Campo cpfCnpj não poder ser nulo")
    @CPFouCNPJ
    private String cpfCnpj;
    private String nacimento;
    private List<String> seguros;
}
