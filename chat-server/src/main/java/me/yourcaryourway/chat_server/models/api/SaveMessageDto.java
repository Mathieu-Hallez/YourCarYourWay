package me.yourcaryourway.chat_server.models.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Builder
public class SaveMessageDto {
    @NotNull
    private Long id;
    @NotNull
    @JsonProperty("parent_message_id")
    private Long parentMessageId;
    @NotNull
    private String text;
    @NotNull
    private String type;
    @JsonProperty("is_read")
    @Nullable
    private Boolean isRead;
    @NotNull
    @JsonProperty("sender_email")
    private String senderEmail;
    @NotNull
    @JsonProperty("receiver_email")
    private String receiverEmail;
}