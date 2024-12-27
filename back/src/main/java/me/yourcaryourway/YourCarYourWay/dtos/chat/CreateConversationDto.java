package me.yourcaryourway.YourCarYourWay.dtos.chat;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import me.yourcaryourway.YourCarYourWay.dtos.ApiResponseDto;

@Data
public class CreateConversationDto implements ApiResponseDto {
    private String subject;
    private String recipient;
    @JsonProperty("message")
    private MessageDto messageDto;
}
