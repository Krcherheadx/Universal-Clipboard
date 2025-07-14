package dev.hisham.universal_clipboard.service;

import dev.hisham.universal_clipboard.dto.CreateClipDTO;
import dev.hisham.universal_clipboard.dto.ResCreateClipDTO;
import dev.hisham.universal_clipboard.model.ClipModel;
import dev.hisham.universal_clipboard.model.UserModel;
import dev.hisham.universal_clipboard.repository.ClipRepository;
import dev.hisham.universal_clipboard.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ClipService {
    final ClipRepository clipRepository;
    final UserRepository userRepository;
    UserModel user;

    public ClipService(ClipRepository clipRepository, UserRepository userRepository) {
        this.clipRepository = clipRepository;
        this.userRepository = userRepository;
    }

    public ResCreateClipDTO createClip(CreateClipDTO createClipDTO) {
        //Check if there's a userId
        if (user == null) {
            List<UserModel> users = userRepository.findAll();
            user = users.stream().findFirst()
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found"));
        }
        ClipModel clipModel = new ClipModel(createClipDTO.content());
        clipModel.setUser(user);
        clipRepository.save(clipModel);
        return new ResCreateClipDTO(clipModel.getCreated_at(), clipModel.getId(), clipModel.getContent());
    }
}
