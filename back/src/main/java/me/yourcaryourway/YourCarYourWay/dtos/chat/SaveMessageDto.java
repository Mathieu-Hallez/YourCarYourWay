package me.yourcaryourway.YourCarYourWay.dtos.chat;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class SaveMessageDto extends MessageDto {
    @JsonProperty("conversation_id")
    private Long conversationId;
}
