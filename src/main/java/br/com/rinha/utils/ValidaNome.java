package br.com.rinha.utils;

import br.com.rinha.domain.Pessoas;
import br.com.rinha.repository.PessoasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

@Component
public class ValidaNome {
    @Autowired
    private PessoasRepository pessoasRepository;

    public boolean validadorNome(String nome) {
        nome = nome.trim();
        if (nome.length() >= 1 && nome.length() <= 32) {
            Optional<Pessoas> pessoas = pessoasRepository.buscarPessoaNomeExiste(nome);
            if (Objects.nonNull(pessoas) && pessoas.isPresent()) return true;
            else return false;
        }
        return false;
    }
}
