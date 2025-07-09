package dev.hisham.universal_clipboard.repository;

import dev.hisham.universal_clipboard.model.Clip;
import org.springframework.stereotype.Repository;

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
}
