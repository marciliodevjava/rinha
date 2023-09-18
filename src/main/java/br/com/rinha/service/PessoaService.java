package br.com.rinha.service;

import br.com.rinha.domain.Pessoas;
import br.com.rinha.dto.request.PessoaDto;
import br.com.rinha.dto.response.PessoaRetornoDto;
import br.com.rinha.exception.ErroBuscarIdSeguroException;
import br.com.rinha.exception.ErroBuscarSeguroVazioException;
import br.com.rinha.exception.ErroSalvarPessoaException;
import br.com.rinha.exception.ErroUuidInvalidoException;
import br.com.rinha.mapper.PessoaMapper;
import br.com.rinha.repository.PessoasRepository;
import br.com.rinha.utils.ValidadorUuid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PessoaService {

    @Autowired
    private PessoaMapper pessoaMapper;
    @Autowired
    private PessoasRepository pessoasRepository;
    @Autowired
    private ValidadorUuid validadorUuid;

    public PessoaRetornoDto salvar(PessoaDto pessoaDto) {
        Pessoas pessoas = pessoaMapper.mapearPessoaSalvar(pessoaDto);
        pessoasRepository.salvarPessoa(pessoas.getId(), pessoas.getNome(), pessoas.getCpfCnpj(), pessoas.getNascimento());
        if (pessoas.getSeguros() != null) {
            pessoas.getSeguros().forEach(seg -> {
                pessoasRepository.salvarSeguros(pessoas.getId(), seg);
            });
        }
        if (pessoas != null) {
            PessoaRetornoDto dto = new PessoaRetornoDto();
            dto = pessoaMapper.mapearPessoaRetornoDto(Optional.of(pessoas));
            return dto;
        }
        throw new ErroSalvarPessoaException();
    }

    public PessoaRetornoDto buscarPessoaId(String id) {
        boolean valida = validadorUuid.isValidUUID(id);
        if (valida == true) {
            PessoaRetornoDto dto = new PessoaRetornoDto();
            List<String> seguros = new ArrayList<>();

            Optional<Pessoas> pessoas = pessoasRepository.buscarPessoa(id);
            if (pessoas.equals(Optional.empty())) throw new ErroBuscarIdSeguroException();
            List<String> seguro = pessoasRepository.buscarSegurosId(id);

            if (!seguro.isEmpty()) {
                seguro.forEach(seg -> {
                    seguros.add(this.extrairSeguro(seg));
                });

                pessoas.get().setSeguros(seguros);
            }

            return dto = pessoaMapper.mapearPessoaRetornoDto(pessoas);
        }
        throw new ErroUuidInvalidoException();
    }

    public List<PessoaRetornoDto> buscarPessoaIdList(String id) {
        boolean valida = validadorUuid.isValidUUID(id);
        if (valida == true) {
            List<PessoaRetornoDto> retornoDto = new ArrayList<>();
            PessoaRetornoDto dto = new PessoaRetornoDto();
            List<String> seguros = new ArrayList<>();

            Optional<Pessoas> pessoas = pessoasRepository.buscarPessoa(id);
            List<String> seguro = pessoasRepository.buscarSegurosId(id);

            if (!seguro.isEmpty()) {
                seguro.forEach(seg -> {
                    seguros.add(this.extrairSeguro(seg));
                });

                pessoas.get().setSeguros(seguros);
            }

            dto = pessoaMapper.mapearPessoaRetornoDto(pessoas);
            retornoDto.add(dto);

            return retornoDto;
        }
        throw new ErroBuscarIdSeguroException();
    }

    public List<PessoaRetornoDto> buscarPessoaSeguros(String id) {
        List<PessoaRetornoDto> listPessoa = new ArrayList<>();
        if (id != null) {
            List<String> listSeguros = extrairId(pessoasRepository.buscarSeguros(id));
            if (Objects.isNull(listSeguros)) throw new ErroBuscarIdSeguroException();
            listSeguros.forEach(a -> {
                PessoaRetornoDto dto = new PessoaRetornoDto();
                dto = this.buscarPessoaId(a);
                listPessoa.add(dto);
            });
//            if(listSeguros.isEmpty()) throw new ErroBuscarSeguroVazioException();
            return listPessoa;
        }

        throw new ErroBuscarIdSeguroException();
    }

    private List<String> extrairSeguro(List<String> strings) {
        List<String> list = new ArrayList<>();
        if (Objects.nonNull(strings)) {
            strings.forEach(seg -> {
                list.add(extrairSeguro(seg));
            });

            return list;
        }
        return null;
    }

    private String extrairSeguro(String seg) {
        String[] partir = seg.split(",");
        return partir[1];
    }

    private String extrairId(String seg) {
        String[] partir = seg.split(",");
        return partir[0];
    }

    private List<String> extrairId(List<String> strings) {
        List<String> list = new ArrayList<>();
        if (Objects.nonNull(strings)) {
            strings.forEach(seg -> {
                list.add(extrairId(seg));
            });

            return list;
        }
        return null;
    }
}
