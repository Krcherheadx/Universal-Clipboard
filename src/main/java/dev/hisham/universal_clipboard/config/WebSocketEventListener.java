package dev.hisham.universal_clipboard.config;

import dev.hisham.universal_clipboard.dto.ResCreateClipDTO;
import dev.hisham.universal_clipboard.service.ClipService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Component;

import java.nio.file.AccessDeniedException;
import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@Component
public class WebSocketEventListener {


    private final ClipService clipService;


    @SubscribeMapping("/user/clips")
    public List<ResCreateClipDTO> sendAllClipsOnSubscribe(Principal principal) throws AccessDeniedException {
        if (principal == null) {
            throw new AccessDeniedException("User not authenticated");
        }
        return clipService.retrieveAllClips(principal.getName());
    }


}

