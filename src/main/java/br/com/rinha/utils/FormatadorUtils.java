package br.com.rinha.utils;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class FormatadorUtils {

    public LocalDate dataStringDate(String nacimento) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            LocalDate dataNascimento = LocalDate.parse(nacimento, formatter);
            return dataNascimento;
        } catch (Exception e) {
            throw new IllegalArgumentException("Formato de data inválido. Use o formato yyyy-MM-dd");
        }
    }

    public String dataDateString(LocalDate nascimento) {
        return nascimento.toString();
    }
}