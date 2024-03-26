package com.example.demo.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.beans.factory.annotation.Autowired;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;


@Documented
@Constraint(validatedBy = AgeValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Autowired
public @interface ValidAge {
	String message() default "Invalid age format. Age must be a positive number with a maximum of two decimal places.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}