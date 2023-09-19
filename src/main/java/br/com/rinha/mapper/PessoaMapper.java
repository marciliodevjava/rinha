package br.com.rinha.mapper;

import br.com.rinha.domain.Pessoas;
import br.com.rinha.dto.request.PessoaDto;
import br.com.rinha.dto.response.PessoaRetornoDto;
import br.com.rinha.utils.FormatadorUtils;
import br.com.rinha.utils.GeradorUuidUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
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
            pessoas.setNome(pessoaDto.getNome());
            pessoas.setCpfCnpj(pessoaDto.getCpfCnpj());
            pessoas.setNascimento(formatadorUtils.dataStringDate(pessoaDto.getNacimento()));
            pessoas.setSeguros(pessoaDto.getSeguros());
            return pessoas;
        }
        return null;
    }

    public PessoaRetornoDto mapearPessoaRetornoDto(Optional<Pessoas> retorno) {
        PessoaRetornoDto dto = new PessoaRetornoDto();
        if (retorno != null) {
            Pessoas pessoas = retorno.get();
            dto.setId(pessoas.getId());
            dto.setNome(pessoas.getNome());
            dto.setCpfCnpj(pessoas.getCpfCnpj());
            dto.setNacimento(formatadorUtils.dataDateString(pessoas.getNascimento()));
            dto.setSeguros(this.verificaSeguro(pessoas.getSeguros()));
            return dto;
        }
        return null;
    }

    public PessoaRetornoDto mapearPessoaRetornoDto(Pessoas retorno) {
        PessoaRetornoDto dto = new PessoaRetornoDto();
        if (retorno != null) {
            Pessoas pessoas = retorno;
            dto.setId(pessoas.getId());
            dto.setNome(pessoas.getNome());
            dto.setCpfCnpj(pessoas.getCpfCnpj());
            dto.setNacimento(formatadorUtils.dataDateString(pessoas.getNascimento()));
            dto.setSeguros(this.verificaSeguro(pessoas.getSeguros()));
            return dto;
        }
        return null;
    }

    private List<String> verificaSeguro(List<String> seguros) {
        if (seguros.isEmpty()) return null;
        return seguros;
    }

    public List<PessoaRetornoDto> mapearPessoaListRetorno(List<Pessoas> pessoas) {
        List<PessoaRetornoDto> listDto = new ArrayList<>();
        if (Objects.nonNull(pessoas)) {
            pessoas.forEach(pes -> {
                PessoaRetornoDto dto = new PessoaRetornoDto();
                dto = this.mapearPessoaRetornoDto(pes);
                listDto.add(dto);
            });
            return listDto;
        }
        return null;
    }
}
