package dev.hisham.universal_clipboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		//Hint: if you're calling a method which returns a value, you can use methodname().var to declare a variable that has same return type
		ConfigurableApplicationContext applicationContext = SpringApplication.run(Application.class, args);
		System.out.println("Hello World");
	}

}
