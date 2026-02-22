package com.chat.chatapp.controller;

import com.chat.chatapp.model.ChatMessage;
import com.chat.chatapp.service.ChatService;
import org.springframework.messaging.handler.annotation.*;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

	private final ChatService chatService;
	private final SimpMessageSendingOperations messagingTemplate;

	public ChatController(ChatService chatService, SimpMessageSendingOperations messagingTemplate) {
		this.chatService = chatService;
		this.messagingTemplate = messagingTemplate;
	}

	// Handles chat messages sent to /app/chat/{roomId}
	@MessageMapping("/chat/{roomId}")
	public void sendMessage(@DestinationVariable String roomId, @Payload ChatMessage message) {
		message.setRoomId(roomId);
		message.setType(ChatMessage.MessageType.CHAT);
		chatService.saveMessage(message);

		// Broadcast to everyone subscribed to /topic/room/{roomId}
		messagingTemplate.convertAndSend("/topic/room/" + roomId, message);
	}

	// Handles join events sent to /app/join/{roomId}
	@MessageMapping("/join/{roomId}")
	public void userJoined(@DestinationVariable String roomId, @Payload ChatMessage message) {
		message.setRoomId(roomId);
		message.setType(ChatMessage.MessageType.JOIN);
		message.setContent(message.getSender() + " joined the room");
		chatService.saveMessage(message);

		messagingTemplate.convertAndSend("/topic/room/" + roomId, message);
	}

	// Handles leave events sent to /app/leave/{roomId}
	@MessageMapping("/leave/{roomId}")
	public void userLeft(@DestinationVariable String roomId, @Payload ChatMessage message) {
		message.setRoomId(roomId);
		message.setType(ChatMessage.MessageType.LEAVE);
		message.setContent(message.getSender() + " left the room");
		chatService.saveMessage(message);

		messagingTemplate.convertAndSend("/topic/room/" + roomId, message);
	}
}