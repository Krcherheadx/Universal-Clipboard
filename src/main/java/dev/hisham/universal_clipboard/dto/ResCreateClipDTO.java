package dev.hisham.universal_clipboard.dto;

import java.time.LocalDateTime;

public record ResCreateClipDTO(
        LocalDateTime createdAt
        , String id,
        String content
) {
}
