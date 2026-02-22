package com.chat.chatapp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
	
	@Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        // Messages from server to client will be prefixed with /topic
        config.enableSimpleBroker("/topic");

        // Messages from client to server will be prefixed with /app
        config.setApplicationDestinationPrefixes("/app");
    }

	@Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws")
                .setAllowedOriginPatterns("*")
                .withSockJS();

        registry.addEndpoint("/ws")
                .setAllowedOriginPatterns("*");
    }
    
}
