package dev.hisham.universal_clipboard.controller;

import dev.hisham.universal_clipboard.dto.AuthUserDTO;
import dev.hisham.universal_clipboard.dto.AuthenticationResponseDTO;
import dev.hisham.universal_clipboard.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<AuthenticationResponseDTO> signup(@Valid @RequestBody AuthUserDTO authUserDTO) {
        AuthenticationResponseDTO response = authService.signup(authUserDTO.username(), authUserDTO.password());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponseDTO> login(@Valid @RequestBody AuthUserDTO authUserDTO) {
        return new ResponseEntity<>(authService.login(authUserDTO.username(), authUserDTO.password()), HttpStatus.OK);
    }

}
