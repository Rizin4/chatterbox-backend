package com.chat.chatapp.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "rooms")
public class ChatRoom {

    @Id
    private String roomId;
    private String roomName;
}