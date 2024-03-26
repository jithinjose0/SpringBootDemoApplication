package com.example.demo;



import java.util.Arrays;


//import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

//import org.springframework.boot.test.context.SpringBootTest;

//@SpringBootTest
@SpringBootApplication
@ComponentScan(basePackages =  ("com.example.demo"))
public class DemoSpring2Application {
    public static void main(String[] args) {
      SpringApplication.run(DemoSpring2Application.class, args);
    }
    
    @Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
 
			System.out.println("Let's inspect the beans provided by Spring Boot:");
 
			String[] beanNames = ctx.getBeanDefinitionNames();
			Arrays.sort(beanNames);
			for (String beanName : beanNames) {
				System.out.println(beanName);
			}
 
		};
	}

}