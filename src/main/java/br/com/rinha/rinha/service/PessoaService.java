package br.com.rinha.rinha.service;

import br.com.rinha.rinha.domain.Pessoas;
import br.com.rinha.rinha.dto.request.PessoaDto;
import br.com.rinha.rinha.dto.response.PessoaRetornoDto;
import br.com.rinha.rinha.exception.ErroSalvarPessoaException;
import br.com.rinha.rinha.mapper.PessoaMapper;
import br.com.rinha.rinha.repository.PessoasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PessoaService {
    @Autowired
    private PessoaMapper pessoaMapper;
    @Autowired
    private PessoasRepository pessoasRepository;

    public PessoaRetornoDto salvar(PessoaDto pessoaDto) {
        Pessoas pessoas = pessoaMapper.mapearPessoaSalvar(pessoaDto);
        Optional<Pessoas> retorno = pessoasRepository.salvarPessoa(pessoas.getId(),
                pessoas.getNome(),
                pessoas.getCpfCnpj(),
                pessoas.getNascimento(),
                pessoas.getSeguros());
        if (retorno.isPresent()){
            PessoaRetornoDto dto = new PessoaRetornoDto();
            dto = pessoaMapper.mapearPessoaRetornoDto(retorno);
            return dto;
        }
        throw new ErroSalvarPessoaException();
    }
}