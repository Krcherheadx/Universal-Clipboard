package dev.hisham.universal_clipboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        //Hint: if you're calling a method which returns a value, you can use methodname().var to declare a variable that has same return type
//        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
        SpringApplication.run(Application.class, args);

        //Hint: you can print all beans using following code
//        Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);
    }

}
