package br.com.rinha.annotations.interfaces;

import br.com.rinha.annotations.CPFCNPJValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CPFCNPJValidator.class)
public @interface NaoNulo {
    String message() default "Campo cpfCnpj não poder ser nulo";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}