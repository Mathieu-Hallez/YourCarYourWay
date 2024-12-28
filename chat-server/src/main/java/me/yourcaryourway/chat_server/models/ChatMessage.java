package me.yourcaryourway.chat_server.models;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChatMessage {
    @NotNull
    private Long id;
    @NotNull
    private Long parentId;
    @NotNull
    private String text;
    @NotNull
    private Boolean isRead;
    @NotNull
    private String senderEmail;
    @NotNull
    private String receiverEmail;
}
