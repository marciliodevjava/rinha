package br.com.rinha.utils;

import org.springframework.stereotype.Component;

@Component
public class ValidadeSeguro {
    public boolean validadeSeguro(String seguro) {
        if (seguro.length() >= 1 && seguro.length() <= 32) return true;
        return false;
    }
}