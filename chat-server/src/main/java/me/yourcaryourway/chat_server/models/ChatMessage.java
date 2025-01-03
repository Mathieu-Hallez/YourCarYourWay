package me.yourcaryourway.chat_server.models;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChatMessage {
    @Nullable
    private Long id;
    @Nullable
    private Long parentId;
    @NotNull
    private String text;
    @Nullable
    private Boolean isRead;
    @NotNull
    private String senderEmail;
    @NotNull
    private String receiverEmail;
    @NotNull
    private MessageType type;
}
