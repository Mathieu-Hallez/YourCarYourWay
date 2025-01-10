package me.yourcaryourway.YourCarYourWay_WebAPI.dtos.chat;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class SaveMessageDto extends AbstractMessageDto {
    @NotNull
    @JsonProperty("parent_message_id")
    private Long parentMessageId;
}
