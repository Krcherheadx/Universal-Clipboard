package dev.hisham.universal_clipboard.dto;

import jakarta.validation.constraints.NotBlank;

//Record is special immutable java class, it's usually used for DTOs
//DTO: is an object that's responsible to transfer data from server to the client or vice versa, while in the other
// hand, entity is responsible to retrieve data from database
public record CreateUserDTO(
        @NotBlank

        String username,


        @NotBlank
        String password
) {
}
