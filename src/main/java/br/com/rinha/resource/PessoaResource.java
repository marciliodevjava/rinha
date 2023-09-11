package br.com.rinha.resource;

import br.com.rinha.dto.request.PessoaDto;
import br.com.rinha.dto.response.PessoaRetornoDto;
import br.com.rinha.exception.ErroBuscarIdSeguroException;
import br.com.rinha.service.PessoaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/pessoa")
public class PessoaResource {
    @Autowired
    private PessoaService pessoaService;

    @PostMapping
    public ResponseEntity<PessoaRetornoDto> salvar(@RequestBody @Valid PessoaDto pessoaDto, UriComponentsBuilder builder) {
        PessoaRetornoDto dto = pessoaService.salvar(pessoaDto);
        URI uri = builder.path("/pessoa").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PessoaRetornoDto> buscar(@PathVariable String id){
        PessoaRetornoDto retorno = pessoaService.buscarPessoaId(id);
        return ResponseEntity.ok(retorno);
    }

    @GetMapping
    public ResponseEntity<List<PessoaRetornoDto>> buscarSeguros(@RequestParam(required = false, name = "id") String id,
                                                               @RequestParam(required = false, name = "seguros") String seguros){
        List<PessoaRetornoDto> retorno;
        if (id != null){
            retorno = pessoaService.buscarPessoaIdList(id);
            return ResponseEntity.ok(retorno);
        } else if (seguros != null){
            retorno = pessoaService.buscarPessoaSeguros(seguros);
            return ResponseEntity.ok(retorno);
        } else {
            throw new ErroBuscarIdSeguroException();
        }
    }
}
