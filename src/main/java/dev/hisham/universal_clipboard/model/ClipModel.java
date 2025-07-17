package dev.hisham.universal_clipboard.model;


import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "Clips")
@Data
@Builder
public class ClipModel {

    @Id
    private String id; // ← This maps to MongoDB's _id field
    private String content;
    private LocalDateTime created_at = LocalDateTime.now();
    @Indexed
    private String username;


}
