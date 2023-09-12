package br.com.rinha.infra;

import br.com.rinha.enuns.MensagemEnum;
import br.com.rinha.exception.ErroBuscarIdSeguroException;
import br.com.rinha.exception.ErroUuidInvalidoException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class TratadorErros {
    @Value("${spring.application.name}")
    private String projeto;
    @Autowired
    private HttpServletRequest request;

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<ErroResponse> cpfDuplicado(SQLIntegrityConstraintViolationException ex) {
        ErroResponse erro = new ErroResponse();
        erro.setStatus(HttpStatus.BAD_REQUEST.value());
        erro.setMensagem(Collections.singletonList(MensagemEnum.ERRO_CPF_DUPLICADO_EXCEPTION.getMensagem()));
        erro.setTimestamp(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        erro.setEndpoint(request.getRequestURI());
        erro.setProjeto(projeto);
        return new ResponseEntity<>(erro, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroResponse> campoInválido(MethodArgumentNotValidException ex) {
        ErroResponse erro = new ErroResponse();
        erro.setStatus(HttpStatus.BAD_REQUEST.value());
        erro.setTimestamp(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        erro.setEndpoint(request.getRequestURI());
        erro.setProjeto(projeto);

        BindingResult bindingResult = ex.getBindingResult();
        List<String> mensagensDeErro = new ArrayList<>();

        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            String mensagem = fieldError.getDefaultMessage();
            mensagensDeErro.add(mensagem);
        }

        erro.setMensagem(mensagensDeErro);

        return new ResponseEntity<>(erro, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ErroBuscarIdSeguroException.class)
    public ResponseEntity<ErroResponse> erroBuscarIdSeguro(ErroBuscarIdSeguroException ex) {

        ErroResponse erro = new ErroResponse();
        erro.setStatus(HttpStatus.BAD_REQUEST.value());
        erro.setMensagem(Collections.singletonList(MensagemEnum.ERRO_BUSCAR_ID_SEGUROS_EXCEPTION.getMensagem()));
        erro.setTimestamp(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        erro.setEndpoint(request.getRequestURI());
        erro.setProjeto(projeto);
        return new ResponseEntity<>(erro, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErroResponse> corpoDaSolicitacaoAusente(HttpMessageNotReadableException ex) {

        ErroResponse erro = new ErroResponse();
        erro.setStatus(HttpStatus.BAD_REQUEST.value());
        erro.setMensagem(Collections.singletonList(MensagemEnum.ERRO_CORPO_SOLICITACAO_AUXENTE_EXCEPTION.getMensagem()));
        erro.setTimestamp(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        erro.setEndpoint(request.getRequestURI());
        erro.setProjeto(projeto);
        return new ResponseEntity<>(erro, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErroResponse> metodoInvalido(HttpRequestMethodNotSupportedException ex) {

        String resposta = this.capturaTipoRequisição(ex.getMessage());
        ErroResponse erro = new ErroResponse();
        erro.setStatus(HttpStatus.BAD_REQUEST.value());
        erro.setMensagem(Collections.singletonList(MensagemEnum.ERRO_METODO_INVALIDO_EXCEPTION.getMensagem() + resposta));
        erro.setTimestamp(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        erro.setEndpoint(request.getRequestURI());
        erro.setProjeto(projeto);
        return new ResponseEntity<>(erro, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ErroResponse> erroCansoNulo(NoSuchElementException ex) {

        String resposta = this.capturaTipoRequisição(ex.getMessage());
        ErroResponse erro = new ErroResponse();
        erro.setStatus(HttpStatus.BAD_REQUEST.value());
        erro.setMensagem(Collections.singletonList(MensagemEnum.ERRO_ID_NAO_EXCEPTION.getMensagem() + resposta));
        erro.setTimestamp(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        erro.setEndpoint(request.getRequestURI());
        erro.setProjeto(projeto);
        return new ResponseEntity<>(erro, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ErroUuidInvalidoException.class)
    public ResponseEntity<ErroResponse> uuidInvalido(ErroUuidInvalidoException ex) {

        ErroResponse erro = new ErroResponse();
        erro.setStatus(HttpStatus.BAD_REQUEST.value());
        erro.setMensagem(Collections.singletonList(MensagemEnum.ERRO_UUID_IVALIDO_EXCEPTION.getMensagem()));
        erro.setTimestamp(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        erro.setEndpoint(request.getRequestURI());
        erro.setProjeto(projeto);
        return new ResponseEntity<>(erro, HttpStatus.BAD_REQUEST);
    }

    private String capturaTipoRequisição(String message) {
        String[] requisicao = {"PUT","PATCH","DELETE","OPTIONS","HEAD"};
        for (int i = 0; i <= requisicao.length; i++){
            if(message.contains(requisicao[i])){
                return requisicao[i];
            }
        }
        return "";
    }
}
