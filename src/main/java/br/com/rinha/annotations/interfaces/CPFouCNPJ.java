package br.com.rinha.annotations.interfaces;

import br.com.rinha.annotations.CPFCNPJValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CPFCNPJValidator.class)
public @interface CPFouCNPJ {
    String message() default "CPF ou CNPJ inválido";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
