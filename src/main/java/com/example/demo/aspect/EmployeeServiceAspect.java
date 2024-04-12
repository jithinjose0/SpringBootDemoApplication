package com.example.demo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpServerErrorException.InternalServerError;

import com.example.demo.models.Employee;

@Aspect
@Component
public class EmployeeServiceAspect {

//	@Pointcut("execution(* com.example.demo.services.EmployeeService.createEmployee(..)) && args(employee)")
//	private void forEmployeeService(Employee employee) {
//	}

	@Before("execution(* com.example.demo.services.EmployeeService.createEmployee(..)) && args(employee)")
	public void before(JoinPoint joinPoint, Employee employee) {
		String name = employee.getName();
		System.out.println("Before method:" + joinPoint.getSignature());
		System.out.println("Before Name:" + name);
	}

	@After("execution(* com.example.demo.services.EmployeeService.createEmployee(..)) && args(employee)")
	public void after(JoinPoint joinPoint, Employee employee) {
		String name = employee.getName();
		System.out.println("After method:" + joinPoint.getSignature());
		System.out.println("After Name:" + name);
	}

	@Around(value = "execution(* com.example.demo.services.EmployeeService.createEmployee(..)) && args(employee)")
	public void aroundAdvice(ProceedingJoinPoint jp, Employee employee) throws Throwable {
		System.out.println(
				"The method aroundAdvice() before invokation of the method " + jp.getSignature().getName() + " method");
		try {
			jp.proceed();
		} finally {

		}
		System.out.println(
				"The method aroundAdvice() after invokation of the method " + jp.getSignature().getName() + " method");
	}

	// implementing after returning advice
	@AfterReturning(value = "execution(* com.example.demo.services.EmployeeService.*(..))", returning = "savedEmployee")
	public void afterReturningAdvice(JoinPoint joinPoint, Employee savedEmployee) {
		System.out.println("After Returing method:" + joinPoint.getSignature());
		System.out.println(savedEmployee);
	}
	
	
	// if the function is internal server error this AfterThrowing will work
//	@AfterThrowing(value = "execution(* com.example.demo.services.EmployeeService.*(..))", throwing = "ex")
//	public void afterThrowingAdvice(JoinPoint joinPoint, InternalServerError ex) {
//		System.out.println("After Throwing exception in method:" + joinPoint.getSignature());
//		System.out.println("Exception is:" + ex.getMessage());
//	}
	
//	
//	@After(value = "execution(* com.example.demo.services.EmployeeService.throwException(..))")
//	public void afterThrowingAdvice(JoinPoint joinPoint) {
//		System.out.println("########After Throwing exception in method:" + joinPoint.getSignature());
//		
//	}

}
