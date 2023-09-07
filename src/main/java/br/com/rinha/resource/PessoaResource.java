package br.com.rinha.rinha.resource;

import br.com.rinha.rinha.dto.request.PessoaDto;
import br.com.rinha.rinha.dto.response.PessoaRetornoDto;
import br.com.rinha.rinha.service.PessoaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

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
}
