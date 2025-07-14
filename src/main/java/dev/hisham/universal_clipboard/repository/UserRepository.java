package dev.hisham.universal_clipboard.repository;

import dev.hisham.universal_clipboard.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//Repository act as an access layer
public interface UserRepository extends JpaRepository<UserModel, String> {
//    Since we use Spring Data framewrok it'll handle the implementation of the repository, in addition, it handles
//    custom-methods
Optional<UserModel> findByUsername(String username);

}
