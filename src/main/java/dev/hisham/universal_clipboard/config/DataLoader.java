package dev.hisham.universal_clipboard.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.hisham.universal_clipboard.model.UserModel;
import dev.hisham.universal_clipboard.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;

//It will run after all the dependencies are injected
//You should to put it as @component, so that, spring will recognize it
@Component
public class DataLoader implements CommandLineRunner {

    private final ObjectMapper objectMapper;
    private final UserService userService;
    public DataLoader(ObjectMapper objectMapper, UserService userService) {
        this.objectMapper = objectMapper;
        this.userService = userService;
    }
    @Override
    public void run(String... args) throws Exception {
        //read from the file then store users, ensures that websockt bug will not appear
        //it's called try-with-resource
        try(InputStream inputStream = TypeReference.class.getResourceAsStream("/data/DUMMY-USERS.json")){
            List<UserModel> users= userService.saveAllUsers(objectMapper.readValue(inputStream, new TypeReference<List<UserModel>>() {
            }));
            users.stream().forEach(user ->{
                System.out.println("-------------------------------");
                System.out.println(user.getId());
                System.out.println(user.getUsername());
                System.out.println("-------------------------------");

            });

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
