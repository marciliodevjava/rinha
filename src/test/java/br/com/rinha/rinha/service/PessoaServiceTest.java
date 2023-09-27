package br.com.rinha.rinha.service;

import br.com.rinha.domain.Pessoas;
import br.com.rinha.dto.request.PessoaDto;
import br.com.rinha.dto.response.PessoaRetornoDto;
import br.com.rinha.exception.ErroBuscarIdSeguroException;
import br.com.rinha.exception.ErroUuidInvalidoException;
import br.com.rinha.mapper.PessoaMapper;
import br.com.rinha.repository.PessoasRepository;
import br.com.rinha.service.PessoaService;
import br.com.rinha.utils.ValidaNome;
import br.com.rinha.utils.ValidadorUuid;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PessoaServiceTest {

    @Mock
    private PessoaMapper pessoaMapper;

    @Mock
    private PessoasRepository pessoasRepository;

    @Mock
    private ValidadorUuid validadorUuid;

    @Mock
    private ValidaNome validaNome;

    @InjectMocks
    private PessoaService pessoaService;
    private final String UUID = "cafc5729-a332-448a-996b-4bea910ff7d1";

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testBuscarPessoaIdComUUIDInvalido() {
        String id = UUID;
        when(validadorUuid.isValidUUID(id)).thenReturn(false);

        assertThrows(ErroUuidInvalidoException.class, () -> {
            pessoaService.buscarPessoaId(id);
        });
    }

    @Test
    public void testBuscarPessoaIdComPessoaNaoEncontrada() {
        String id = UUID;
        when(validadorUuid.isValidUUID(id)).thenReturn(true);
        when(pessoasRepository.buscarPessoa(id)).thenReturn(Optional.empty());

        assertThrows(ErroBuscarIdSeguroException.class, () -> {
            pessoaService.buscarPessoaId(id);
        });
    }
}

