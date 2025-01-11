package me.yourcaryourway.chat_server.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Builder
public class SaveMessageDto {
    @Nullable
    @JsonProperty("parent_message_id")
    private Long parentMessageId;
    @NotNull
    private String text;
    @NotNull
    private String type;
    @NotNull
    @JsonProperty("sender_email")
    private String senderEmail;
    @NotNull
    @JsonProperty("receiver_email")
    private String receiverEmail;
}