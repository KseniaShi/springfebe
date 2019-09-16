package com.spring.wsFB.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import com.spring.wsFB.interceptor.HttpHandshakeInterceptor;

// @Configuration indicates that it is a Spring configuration class. 
// @EnableWebSocketMessageBroker enables WebSocket message handling
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer{

	
	@Autowired
	private HttpHandshakeInterceptor handshakeInterceptor;
	
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/ws").withSockJS().setInterceptors(handshakeInterceptor);	
		}
	
// implements the default method in WebSocketMessageBrokerConfigurer to configure 
//the message broker.
//enableSimpleBroker() enables a simple memory-based message broker to carry the messages back to 
//the client on destinations prefixed with "/topic". 
//It also designates the "/app" prefix for messages that are bound for @MessageMapping-annotated methods.
	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		registry.setApplicationDestinationPrefixes("/app");
		registry.enableSimpleBroker("/topic");
	}
}
