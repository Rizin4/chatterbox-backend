package com.chat.chatapp.service;

import com.chat.chatapp.model.ChatMessage;
import com.chat.chatapp.model.ChatRoom;
import com.chat.chatapp.repository.ChatMessageRepository;
import com.chat.chatapp.repository.ChatRoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService {

	private final ChatMessageRepository messageRepository;
	private final ChatRoomRepository roomRepository;

	public ChatService(ChatMessageRepository messageRepository, ChatRoomRepository roomRepository) {
		this.messageRepository = messageRepository;
		this.roomRepository = roomRepository;
	}

	// Save a message to DB
	public ChatMessage saveMessage(ChatMessage message) {
		return messageRepository.save(message);
	}

	// Get all messages for a room (chat history)
	public List<ChatMessage> getMessagesByRoom(String roomId) {
		return messageRepository.findByRoomId(roomId);
	}

	// Create a new room
	public ChatRoom createRoom(ChatRoom room) {
		return roomRepository.save(room);
	}

	// Get all rooms
	public List<ChatRoom> getAllRooms() {
		return roomRepository.findAll();
	}

	// Check if room exists
	public boolean roomExists(String roomId) {
		return roomRepository.existsById(roomId);
	}
}