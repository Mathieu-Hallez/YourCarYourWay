package me.yourcaryourway.YourCarYourWay_WebAPI.dtos.chat;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import me.yourcaryourway.YourCarYourWay_WebAPI.dtos.ApiResponseDto;

@Data
public class RequestConversationDto implements ApiResponseDto {
    @JsonProperty("sender_email")
    private String senderEmail;
    @JsonProperty("receiver_email")
    private String receiverEmail;
}
