package dev.hisham.universal_clipboard.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.hisham.universal_clipboard.service.AuthService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//It will run after all the dependencies are injected
//You should to put it as @component, so that, spring will recognize it
@Component
public class DataLoader implements CommandLineRunner {

    private final ObjectMapper objectMapper;
    private final AuthService authService;

    public DataLoader(ObjectMapper objectMapper, AuthService authService) {
        this.objectMapper = objectMapper;
        this.authService = authService;
    }

    @Override
    public void run(String... args) {
        //read from the file then store users, ensures that websockt bug will not appear
        //it's called try-with-resource
//        try(InputStream inputStream = TypeReference.class.getResourceAsStream("/data/DUMMY-USERS.json")){
//            List<UserModel> users= userService.saveAllUsers(objectMapper.readValue(inputStream, new TypeReference<List<UserModel>>(){}));


//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }

    }
}
