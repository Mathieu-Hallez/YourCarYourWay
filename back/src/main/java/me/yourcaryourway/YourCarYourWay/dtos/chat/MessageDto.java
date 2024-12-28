package me.yourcaryourway.YourCarYourWay.dtos.chat;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import me.yourcaryourway.YourCarYourWay.dtos.ApiResponseDto;

@Data
public class MessageDto implements ApiResponseDto {
    private String text;
    @JsonProperty("sender_email")
    private String senderEmail;
}