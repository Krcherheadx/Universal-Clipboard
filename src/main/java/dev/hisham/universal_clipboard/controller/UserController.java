package dev.hisham.universal_clipboard.controller;

import dev.hisham.universal_clipboard.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {


    private final UserService userService;


//
//    @ResponseStatus(HttpStatus.CREATED)
//    @PostMapping("/signup")
//    public ResponseEntity<AuthenticationResponse> createUser(@Valid @RequestBody CreateUserDTO createUserDTO) {
//
//    }
//
//    @PostMapping("/login")
//    public ResponseEntity<AuthenticationResponse> authUser()

}
