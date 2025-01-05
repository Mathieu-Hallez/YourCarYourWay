package me.yourcaryourway.chat_server.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class ChatNotification {
    @NotNull
    private Long id;
    @Nullable
    @JsonProperty("parent")
    private Long parent;
    @NotNull
    private String text;
    @NotNull
    @JsonProperty("is_read")
    private Boolean isRead;
    @NotNull
    private String sender;
    @NotNull
    private String receiver;
    @NotNull
    private MessageType type;
    @NotNull
    @JsonProperty("created_at")
    private String createdAt;
}
