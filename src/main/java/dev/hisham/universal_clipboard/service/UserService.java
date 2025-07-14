package dev.hisham.universal_clipboard.service;


import dev.hisham.universal_clipboard.dto.CreateUserDTO;
import dev.hisham.universal_clipboard.model.UserModel;
import dev.hisham.universal_clipboard.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserModel createUser(CreateUserDTO createUserDTO) {
        //Check if there's an existing user holds the same username

        if (userRepository.findByUsername(createUserDTO.username()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username not found");
        }
        UserModel userModel = new UserModel(createUserDTO.username(), createUserDTO.password());
        return userRepository.save(userModel);
    }

    public List<UserModel> saveAllUsers(List<UserModel> userModels) {
        System.out.println("REACHED");
        return userRepository.saveAll(userModels);
    }
}
