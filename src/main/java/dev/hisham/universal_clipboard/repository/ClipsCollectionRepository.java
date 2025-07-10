package dev.hisham.universal_clipboard.repository;

import dev.hisham.universal_clipboard.model.Clip;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ClipsCollectionRepository {
    private final List<Clip> clips = new ArrayList<>();

    public ClipsCollectionRepository() {

    }

    public List<Clip> getClips() {
        return clips;
    }

    public Optional<Clip> getClipById(String id) {
        return clips.stream().filter(clip -> clip.id().equals(id)).findFirst();
    }

    public List<Clip> addClip(Clip clip) {
        clips.add(clip);
        return clips;
    }

    //This method will run after the bean is created and the dependencies are injected
    @PostConstruct
    public void init() {
        Clip clip = new Clip("Hisham", "Hello", "1212", LocalDateTime.now());
        clips.add(clip);
    }
}
