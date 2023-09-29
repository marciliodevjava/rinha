package br.com.rinha.annotations;

import br.com.rinha.annotations.interfaces.CPFouCNPJ;
import br.com.rinha.annotations.interfaces.NaoNulo;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NaoNuloValidator implements ConstraintValidator<NaoNulo, String> {

    @Override
    public void initialize(NaoNulo constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }
        if (value.length() <= 1 && value.length() > 43) {
            return false;
        }
        return value.length() >= 3 || value.length() <= 132;
    }
}

