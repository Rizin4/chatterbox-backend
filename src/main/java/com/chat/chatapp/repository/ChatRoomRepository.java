package com.chat.chatapp.repository;

import com.chat.chatapp.model.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, String> {
	
}