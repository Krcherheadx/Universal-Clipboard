package dev.hisham.universal_clipboard.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration //Indicating to Spring that's a config. class
@EnableWebSocketMessageBroker //As the name suggest, the websocket's messages we'll be backed by a message broker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic"); //Enable simple in-memory message broker that will holds that messages that sent back to the subscribers
        config.setApplicationDestinationPrefixes("/app"); // Register a prefix for the method that annotated with @MessageMapping
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws").setAllowedOriginPatterns("*").withSockJS();// allow all origins (newer way)
        //This method will register the websocket connection endpoint
    }

}
