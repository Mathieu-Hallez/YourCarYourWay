package me.yourcaryourway.chat_server.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateConversationDto {
    @NotNull
    private String subject;
    @NotNull
    @JsonProperty("message")
    private CreateMessageDto messageDto;
}
