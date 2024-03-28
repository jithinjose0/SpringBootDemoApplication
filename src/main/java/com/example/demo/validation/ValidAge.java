package com.example.demo.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.beans.factory.annotation.Autowired;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;


@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Autowired
@Constraint(validatedBy = AgeValidator.class)
public @interface ValidAge {
    String message() default "Custom validation error message";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
 