package br.com.rinha.resource;

import br.com.rinha.dto.request.PessoaDto;
import br.com.rinha.dto.response.PessoaRetornoDto;
import br.com.rinha.exception.ErroBuscarIdSeguroException;
import br.com.rinha.service.PessoaService;
import br.com.rinha.utils.ValidadeSeguro;
import br.com.rinha.utils.ValidadorUuid;
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
    private ValidadorUuid validadorUuid;
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
    public ResponseEntity<List<PessoaRetornoDto>> buscarSeguros(@RequestParam (required = true, name = "t") String termo) {

        List<PessoaRetornoDto> retorno;
        boolean validaTermno = validadorUuid.isValidUUID(termo);
        boolean validaSeguro = validadeSeguro.validadeSeguro(termo);
        if (validaTermno == true) {
            retorno = pessoaService.buscarPessoaIdList(termo);
            return ResponseEntity.ok(retorno);
        } else if (validaSeguro == true) {
            retorno = pessoaService.buscarPessoaSeguros(termo);
            return ResponseEntity.ok(retorno);
        } else {
            throw new ErroBuscarIdSeguroException();
        }
    }
}
