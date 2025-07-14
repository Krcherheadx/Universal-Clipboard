package dev.hisham.universal_clipboard.config;

import dev.hisham.universal_clipboard.dto.ResCreateClipDTO;
import dev.hisham.universal_clipboard.service.ClipService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;

import java.security.Principal;
import java.util.List;

@Component
public class WebSocketEventListener {


    private static final Logger LOGGER = LoggerFactory.getLogger(WebSocketEventListener.class);
    private final SimpMessageSendingOperations messagingTemplate;
private ClipService clipService;

    public WebSocketEventListener(SimpMessageSendingOperations messagingTemplate, ClipService clipService) {
        this.messagingTemplate = messagingTemplate;
        this.clipService = clipService;
    }

    @EventListener
    public void handleSubscription(SessionSubscribeEvent event) {
        StompHeaderAccessor sha = StompHeaderAccessor.wrap(event.getMessage());
        String destination = sha.getDestination();
        System.out.println("REACHEDMMMMMMMMM");
        if ("/user/topic/clips".equals(destination)) {
            List<ResCreateClipDTO> clips = clipService.retrieveAllClips();

            // This identifies the client session
            Principal principal = sha.getUser();
            if (principal != null) {
                messagingTemplate.convertAndSendToUser(principal.getName(), "/topic/clips", clips);
            } else {
                // fallback for anonymous clients
                String sessionId = sha.getSessionId();
                messagingTemplate.convertAndSend("/user/" + sessionId + "/topic/clips", clips);
            }
        }
    }

}

