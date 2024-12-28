package me.yourcaryourway.chat_server.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class ChatNotification {
    private String id;
    private String senderEmail;
    private String receiverEmail;
    private String content;
}
