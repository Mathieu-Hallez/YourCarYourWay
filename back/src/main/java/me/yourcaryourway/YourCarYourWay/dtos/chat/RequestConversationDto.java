package me.yourcaryourway.YourCarYourWay.dtos.chat;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import me.yourcaryourway.YourCarYourWay.dtos.ApiResponseDto;

@Data
public class RequestConversationDto implements ApiResponseDto {
    @JsonProperty("sender_email")
    private String senderEmail;
    @JsonProperty("receiver_email")
    private String receiverEmail;
}
