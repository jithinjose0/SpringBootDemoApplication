package com.example.demo.models;

//import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.NotEmpty;
//import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Pattern;
//import javax.validation.constraints.Size;

import com.example.demo.validation.ValidAge;

//import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "persons")
public class Students {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	
	private String last_name;
	
	@NotNull
    @NotEmpty
    @Size(min = 1, max = 10)
    @Pattern(regexp = "[a-zA-Z0-9]+")
	@NotBlank(message = "First Name is mandatory")
	private String first_name;
	
//	@Min(value = 0L, message = "The value must be positive")
//	@Pattern(regexp = "^(0\\.[1-9][0-9]?|[1-9][0-9]*\\.(0[1-9]|1[0-2]))$")
	@ValidAge(message = "invalid input")
	private double age;
	
	public int getId() {
		return id;
	}	

	public void setId(int id) {
		this.id = id;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public double getAge() {
		return age;
	}
	
//	public void setAge(double someDouble) {
//	    if (!isValidDouble(someDouble)) {
//	      throw new IllegalArgumentException("Invalid value for someDouble");
//	    }
//	    else {
//	    	this.age = someDouble;
//	    }
////	    this.age = someDouble;
//	 }
//
//	  private boolean isValidDouble(double value) {
//	    String valueStr = Double.toString(value);
//	    return valueStr.matches("\\d+(\\.([1-9]|1[0-2]))?");
//	  }

	public void setAge(double age) {
		this.age = age;
	}

}
