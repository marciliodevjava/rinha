package br.com.rinha.utils;

import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class ValidadorUuid {
    private static final Pattern UUID_PATTERN = Pattern.compile(
            "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$");

    public boolean isValidUUID(String uuid) {
        return UUID_PATTERN.matcher(uuid).matches();
    }
}



