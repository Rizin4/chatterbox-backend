package com.chat.chatapp.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "messages")
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sender;
    private String content;
    private String roomId;
    private LocalDateTime timestamp;

    @Enumerated(EnumType.STRING)
    private MessageType type;

    public enum MessageType {
        CHAT, JOIN, LEAVE
    }

    @PrePersist
    public void prePersist() {
        this.timestamp = LocalDateTime.now();
    }
}