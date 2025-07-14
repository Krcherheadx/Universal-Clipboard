package dev.hisham.universal_clipboard.repository;

import dev.hisham.universal_clipboard.model.ClipModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClipRepository extends JpaRepository<ClipModel, String> {
    
}
