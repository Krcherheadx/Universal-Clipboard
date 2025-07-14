package dev.hisham.universal_clipboard.controller;

import dev.hisham.universal_clipboard.dto.CreateUserDTO;
import dev.hisham.universal_clipboard.model.UserModel;
import dev.hisham.universal_clipboard.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {


    private final UserService userService;


    public UserController(UserService userService) {

        this.userService = userService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public UserModel createUser(@Valid @RequestBody CreateUserDTO createUserDTO) {
        return userService.createUser(createUserDTO);
    }

}
