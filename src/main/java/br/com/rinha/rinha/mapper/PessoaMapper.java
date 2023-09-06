package br.com.rinha.rinha.mapper;

import br.com.rinha.rinha.domain.Pessoas;
import br.com.rinha.rinha.dto.request.PessoaDto;
import br.com.rinha.rinha.dto.response.PessoaRetornoDto;
import br.com.rinha.rinha.utils.FormatadorUtils;
import br.com.rinha.rinha.utils.GeradorUuidUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

@Component
public class PessoaMapper {
    @Autowired
    private GeradorUuidUtils geradorUuid;
    @Autowired
    private FormatadorUtils formatadorUtils;

    public Pessoas mapearPessoaSalvar(PessoaDto pessoaDto) {
        if (Objects.nonNull(pessoaDto)) {
            Pessoas pessoas = new Pessoas();
            pessoas.setId(geradorUuid.gerarUuid());
            pessoas.setNome(pessoas.getNome());
            pessoas.setCpfCnpj(pessoas.getCpfCnpj());
            pessoas.setNascimento(formatadorUtils.dataStringDate(pessoaDto.getNacimento()));
            pessoas.setSeguros(pessoaDto.getSeguros());
            return pessoas;
        }
        return null;
    }

    public PessoaRetornoDto mapearPessoaRetornoDto(Optional<Pessoas> retorno) {
        PessoaRetornoDto dto = new PessoaRetornoDto();
        if (retorno != null) {
            Pessoas retPes = retorno.get();
            dto.setId(retPes.getId());
            dto.setNome(retPes.getNome());
            dto.getCpfCnpj();
            dto.setNacimento(formatadorUtils.dataDateString(retPes.getNascimento()));
            dto.setSeguros(retPes.getSeguros());
            return dto;
        }
        return null;
    }
}
