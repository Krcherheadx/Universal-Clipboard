package dev.hisham.universal_clipboard.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class ClipModel {
    @ManyToOne
    @JoinColumn(name = "user_id")
    UserModel user;//The variable name must matches the one in mappedBy
    @Id
    private String id;
    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;
    @CreationTimestamp//Automatically put the value of curr. time right-before inserting (Hibernate feature)
    private LocalDateTime created_at;

    public ClipModel() {
    }

    public ClipModel(String content) {
        this.content = content;
        this.id = UUID.randomUUID().toString();
        this.created_at = LocalDateTime.now();
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @PrePersist //Pre
    public void prePersist() {
        if (this.id == null) {
            this.id = UUID.randomUUID().toString();
        }
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }
}
