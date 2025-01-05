package me.yourcaryourway.chat_server.models.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateMessageDto {
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
