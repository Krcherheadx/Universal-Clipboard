package dev.hisham.universal_clipboard;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        //Hint: if you're calling a method which returns a value, you can use methodname().var to declare a variable that has same return type
//        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
        SpringApplication.run(Application.class, args);

        //Hint: you can print all beans using following code
//        Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);
    }

    //Below method will run after  injecting all the dependencies
    //@Value("${var}") will bring the config. variable that matches the var name :default
    //Also, you can configure your own properties
    //I'll inject dummy-users from the json file located in resource/data directory, to load users into the db
    @Profile("production")//Run this only on dev. !dev is the opposite
    @Bean
    CommandLineRunner commandLineRunner(@Value("${custom.name:mohammed}") String name, @Value("${custom.age}")Integer age) {


        return args -> System.out.println("Hello World!" + "\r\nWelcome " + name + ", Your age is " + age);

    }

}
