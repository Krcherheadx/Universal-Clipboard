package dev.hisham.universal_clipboard.service;


import dev.hisham.universal_clipboard.config.JwtService;
import dev.hisham.universal_clipboard.dto.AuthenticationResponseDTO;
import dev.hisham.universal_clipboard.dto.CreateUserDTO;
import dev.hisham.universal_clipboard.model.UserModel;
import dev.hisham.universal_clipboard.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponseDTO signup(CreateUserDTO signupDto) {
   UserModel user = UserModel.builder().username(signupDto.username()).password(passwordEncoder.encode(signupDto.password())).build();
   userRepository.save(user);
   var jwtToken = jwtService.generateJwtToken(user);
   return AuthenticationResponseDTO.builder().token(jwtToken).build();
}
public AuthenticationResponseDTO login(String username, String password) {
    authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(username, password)
    );
    //If I reached here that means the user is authenticated
    var user = userRepository.findByUsername(username).orElse(null);
    var jwtToken = jwtService.generateJwtToken(user);
    return AuthenticationResponseDTO.builder().token(jwtToken).build();
}

}
