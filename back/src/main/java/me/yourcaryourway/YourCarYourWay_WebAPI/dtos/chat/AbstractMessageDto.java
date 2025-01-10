package me.yourcaryourway.YourCarYourWay_WebAPI.dtos.chat;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import me.yourcaryourway.YourCarYourWay_WebAPI.dtos.ApiResponseDto;

@Data
public abstract class AbstractMessageDto implements ApiResponseDto {
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
