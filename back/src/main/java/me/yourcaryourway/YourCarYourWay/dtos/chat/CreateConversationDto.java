package me.yourcaryourway.YourCarYourWay.dtos.chat;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import me.yourcaryourway.YourCarYourWay.dtos.ApiResponseDto;

@Data
public class CreateConversationDto implements ApiResponseDto {
    @NotNull
    private String subject;
    @NotNull
    @JsonProperty("message")
    private CreateMessageDto messageDto;
}
