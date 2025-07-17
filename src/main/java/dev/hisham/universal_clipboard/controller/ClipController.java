package dev.hisham.universal_clipboard.controller;

import dev.hisham.universal_clipboard.dto.CreateClipDTO;
import dev.hisham.universal_clipboard.dto.ResCreateClipDTO;
import dev.hisham.universal_clipboard.service.ClipService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RequiredArgsConstructor
@RestController
@RequestMapping("/clip")
public class ClipController {


    private final ClipService clipService;

    private final SimpMessagingTemplate simpMessagingTemplate;

    //TODO : implement authentication, deletion of the clip
//
//    @MessageMapping("/add")
//    public ResCreateClipDTO addClip(@Valid @Payload CreateClipDTO createClipDTO, SimpMessageHeaderAccessor headerAccessor) {//user_id
//        // must be extracted from the token

    /// /        headerAccessor.getSessionAttributes().put("username", "lds");?
//        simpMessagingTemplate.convertAndSendToUser();
//        return clipService.createClip(createClipDTO);
//
//    }
    @MessageMapping("/add")
    public void addClip(@Payload CreateClipDTO dto, Principal principal) {
        String username = principal.getName(); // Authenticated by JWT handshake
        ResCreateClipDTO clip = clipService.createClip(dto, username);

        // Securely send only to this user
        simpMessagingTemplate.convertAndSendToUser(username, "/clips", clip);
    }


}