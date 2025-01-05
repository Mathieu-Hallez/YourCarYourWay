package me.yourcaryourway.YourCarYourWay.dtos.chat;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.yourcaryourway.YourCarYourWay.dtos.ApiResponseDto;

@EqualsAndHashCode(callSuper = true)
@Data
public class MessageDto extends AbstractMessageDto {
    @NotNull
    private Long id;
    @Nullable
    @JsonProperty("parent_message_id")
    private Long parentMessageId;
    @NotNull
    @JsonProperty("is_read")
    private Boolean isRead;
    @JsonProperty("created_at")
    @NotNull
    private String createdAt;
}
