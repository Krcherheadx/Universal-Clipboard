package dev.hisham.universal_clipboard.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateClipDTO(

        //It is a validation, similar to class-validator in nest. Note: it will not work unless you put @valid anno. right before @ResquestBody
        @NotBlank
        String content
) {

}
