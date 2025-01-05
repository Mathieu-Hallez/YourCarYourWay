package me.yourcaryourway.chat_server.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChatMessage {
    @NotNull
    private String text;
    @NotNull
    private String senderEmail;
    @NotNull
    private String receiverEmail;
    @NotNull
    private MessageType type;
}
