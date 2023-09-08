package br.com.rinha.annotations;

import br.com.rinha.annotations.interfaces.CPFouCNPJ;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CPFCNPJValidator implements ConstraintValidator<CPFouCNPJ, String> {

    @Override
    public void initialize(CPFouCNPJ constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        if (value.length() < 11 || value.length() > 14) {
            return false;
        }

        return value.length() == 11 || value.length() == 14;
    }
}

