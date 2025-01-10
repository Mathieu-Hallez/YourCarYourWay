package me.yourcaryourway.YourCarYourWay_WebAPI.dtos.chat;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import me.yourcaryourway.YourCarYourWay_WebAPI.dtos.ApiResponseDto;

@Data
public class CreateConversationDto implements ApiResponseDto {
    @NotNull
    private String subject;
    @NotNull
    @JsonProperty("message")
    private CreateMessageDto messageDto;
}
