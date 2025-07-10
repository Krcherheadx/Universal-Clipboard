package dev.hisham.universal_clipboard.model;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record Clip(

        String id,
        //It is a validation, similar to class-validator in nest. Note: it will not work unless you put @valid anno. right before @ResquestBody
        @NotBlank
        String content,
        @NotBlank
        String user_id,

        LocalDateTime created_at
) {

}
