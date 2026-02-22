package com.chat.chatapp.controller;

import com.chat.chatapp.model.ChatMessage;
import com.chat.chatapp.model.ChatRoom;
import com.chat.chatapp.service.ChatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {
	
	private final ChatService chatService;
	
	public RoomController(ChatService chatService) {
		this.chatService = chatService;
	}

    // GET all rooms
    @GetMapping
    public ResponseEntity<List<ChatRoom>> getAllRooms() {
        return ResponseEntity.ok(chatService.getAllRooms());
    }

    // POST create a room
    @PostMapping
    public ResponseEntity<?> createRoom(@RequestBody ChatRoom room) {
        if (room.getRoomId() == null || room.getRoomId().isBlank()) {
            room.setRoomId(UUID.randomUUID().toString().substring(0, 8));
        }
        if (chatService.roomExists(room.getRoomId())) {
            return ResponseEntity.badRequest().body("Room already exists");
        }
        return ResponseEntity.ok(chatService.createRoom(room));
    }

    // GET chat history for a room
    @GetMapping("/{roomId}/messages")
    public ResponseEntity<List<ChatMessage>> getMessages(@PathVariable String roomId) {
        return ResponseEntity.ok(chatService.getMessagesByRoom(roomId));
    }
    
}
