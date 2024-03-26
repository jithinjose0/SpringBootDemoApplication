package com.example.demo.validation;




import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


public class AgeValidator implements ConstraintValidator<ValidAge, Double> {

	  private final String message;

	  public AgeValidator(ValidAge constraintAnnotation) {
	    this.message = constraintAnnotation.message();
	  }

	  public boolean isValid(Double value, ConstraintValidatorContext context) {
	    if (value == null) {
	      return true; // Allow null values if needed, adjust as necessary
	    }
	    String regex = "\\d+(\\.([1-9]|1[0-2]))?";
	    return value >= 0 && value.toString().matches(regex);
	  }

	  @Override
	  public void initialize(ValidAge constraintAnnotation) {
	    // Nothing to initialize in this case
	  }
	}

