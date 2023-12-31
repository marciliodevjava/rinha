package br.com.rinha.resource;

import br.com.rinha.dto.request.PessoaDto;
import br.com.rinha.dto.response.PessoaRetornoDto;
import br.com.rinha.exception.ErroBuscarSeguroVazioException;
import br.com.rinha.service.PessoaService;
import br.com.rinha.utils.ValidaNome;
import br.com.rinha.utils.ValidadeSeguro;
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
    @Autowired
    private ValidaNome validaNome;
    @Autowired
    private ValidadeSeguro validadeSeguro;

    @PostMapping
    public ResponseEntity<PessoaRetornoDto> salvar(@RequestBody @Valid PessoaDto pessoaDto, UriComponentsBuilder builder) {
        PessoaRetornoDto dto = pessoaService.salvar(pessoaDto);
        URI uri = builder.path("/pessoa").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PessoaRetornoDto> buscar(@PathVariable String id) {
        PessoaRetornoDto retorno = pessoaService.buscarPessoaId(id);
        return ResponseEntity.ok(retorno);
    }

    @GetMapping
    public ResponseEntity<List<PessoaRetornoDto>> buscarSeguros(@RequestParam(required = true, name = "t") String termo) {
        boolean validaSeguro = false;
        List<PessoaRetornoDto> retorno;
        boolean validaTermno = validaNome.validadorNome(termo);
        if (!validaTermno) validaSeguro = validadeSeguro.validadeSeguro(termo);
        if (validaTermno == true) {
            retorno = pessoaService.buscarPessoaNomeList(termo);
            return ResponseEntity.ok(retorno);
        } else if (validaSeguro == true) {
            retorno = pessoaService.buscarPessoaSeguros(termo);
            return ResponseEntity.ok(retorno);
        } else {
            throw new ErroBuscarSeguroVazioException();
        }
    }
}