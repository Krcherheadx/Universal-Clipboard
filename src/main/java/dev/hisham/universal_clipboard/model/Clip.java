package dev.hisham.universal_clipboard.model;

import java.time.LocalDateTime;

public record Clip(

        String id,
        String content,
        String user_id,
        LocalDateTime created_at
) {

}
