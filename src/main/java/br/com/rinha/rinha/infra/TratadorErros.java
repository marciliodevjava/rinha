package br.com.rinha.rinha.infra;

import br.com.rinha.rinha.enuns.MensagemEnum;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;

@RestControllerAdvice
public class TratadorErros {
    @Value("${spring.application.name}")
    private String projeto;
    @Autowired
    private HttpServletRequest request;

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<ErroResponse> cpfDuplicado(SQLIntegrityConstraintViolationException ex){
        ErroResponse erro = new ErroResponse();
        erro.setStatus(HttpStatus.BAD_REQUEST.value());
        erro.setMensagem(Collections.singletonList(MensagemEnum.ERRO_CPF_DUPLICADO_EXCEPTION.getMensagem()));
        erro.setTimestamp(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        erro.setEndpoint(request.getRequestURI());
        erro.setProjeto(projeto);
        return new ResponseEntity<>(erro, HttpStatus.BAD_REQUEST);
    }
}
