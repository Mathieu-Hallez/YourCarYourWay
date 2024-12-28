package me.yourcaryourway.YourCarYourWay.dtos.chat;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import me.yourcaryourway.YourCarYourWay.dtos.ApiResponseDto;

@Data
public class MessageDto implements ApiResponseDto {
    @NotNull
    private Long id;
    @NotNull
    private String text;
    @NotNull
    private String type;
    @JsonProperty("is_read")
    @Nullable
    private Boolean isRead;
    @NotNull
    @JsonProperty("sender_email")
    private String senderEmail;
    @NotNull
    @JsonProperty("receiver_email")
    private String receiverEmail;
    @JsonProperty("created_at")
    @Nullable
    private String createdAt;
}
