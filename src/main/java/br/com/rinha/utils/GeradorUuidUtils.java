package br.com.rinha.rinha.utils;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class GeradorUuidUtils {
    public String gerarUuid() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}
