//package com.example.demo.validation;
//
//
//
//
//import jakarta.validation.ConstraintValidator;
//import jakarta.validation.ConstraintValidatorContext;
//
//
//
//public class AgeValidator implements ConstraintValidator<ValidAge, Double> {
//	 
//	@Override
// 
//	public void initialize(ValidAge constraintAnnotation) {
// 
//	}
// 
//	@Override
//	public boolean isValid(Double employee_age, ConstraintValidatorContext context) {
//   	 if (employee_age == null) {
//            return false; // Handle null values as invalid
//        }
// 
//        String ageString = employee_age.toString(); // Convert BigDecimal to String (preserves precision)
// 
//        // Split into parts, handling edge cases (empty string, single dot)
//        String[] parts = ageString.split("\\.");
//        if (parts.length < 1 || parts.length > 2) {
//            return false; // Invalid format if not 1 or 2 decimal parts
//        }
// 
//        int leftDecimal;
//        int rightDecimal = 0; // Initialize rightDecimal to 0 (handles cases with no decimal part)
// 
//        try {
//            leftDecimal = Integer.parseInt(parts[0]);
//            if (parts.length == 2) {
//                rightDecimal = Integer.parseInt(parts[1]);
//            }
//        } catch (NumberFormatException e) {
//            return false; // Handle non-numeric values
//        }
// 
//        // Validate left decimal position (0-99)
//        if (leftDecimal < 0 || leftDecimal > 99) {
//            return false;
//        }
// 
//        // Validate right decimal position (1-12, trim leading 0 except 10)
//        if (rightDecimal == 0 && !ageString.equals("10.0")) {
//            return false;
//        } else if (rightDecimal < 1 || rightDecimal > 12) {
//            return false;
//        }
// 
//        return true;
//    }
//}
// 
