package dev.hisham.universal_clipboard.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity(name = "User")//it's not necessary, but, it's a best practice
@Table(name = "user", uniqueConstraints = {@UniqueConstraint(columnNames = {"username"}, name = "user_username_unique")})
public class UserModel {
    @Id
    @Column(columnDefinition = "CHAR(36)") //You can change any column attribute through this annotation
    private String id;
    @Column(nullable = false, columnDefinition = "VARCHAR(55)")
    private String username;
    @Column(nullable = false)
    private String password;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user") // There's multiple types of cascading, mappedBy value must
    // match the variable name in the Clip model
    private List<ClipModel> clips;

    protected UserModel() {
    } //Since I've created an auxiliary constructor, I need to define an empty one, since it will be used by

    public UserModel(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public List<ClipModel> getClips() {
        return clips;
    }
    // Hibernate to fetch users from the db then fills them in empty users objects, since it doesn't know how it fills the auxiliary constructor.

    public void setClips(List<ClipModel> clipModels) { //Since the cascade type is ALL, that implies that persist type is
        // included, persist type helps us to auto-save the clips event though we haven't saved it manually.
        this.clips = clipModels;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    @PrePersist //This method will run automatically before inserting into User's tables (persist in JPA synonym of
    // save)
    public void generateId() {
        if (id == null) id = UUID.randomUUID().toString();
    }
}

