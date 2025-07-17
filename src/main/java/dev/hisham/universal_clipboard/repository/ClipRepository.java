package dev.hisham.universal_clipboard.repository;

import dev.hisham.universal_clipboard.model.ClipModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ClipRepository extends MongoRepository<ClipModel, String> {
    List<ClipModel> findAllByUsername(String username);
}
