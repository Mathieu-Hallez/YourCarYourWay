package me.yourcaryourway.chat_server.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class MessageDto {
    @NotNull
    private Long id;
    @Nullable
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
    @JsonProperty("created_at")
    @Nullable
    private String createdAt;
}
