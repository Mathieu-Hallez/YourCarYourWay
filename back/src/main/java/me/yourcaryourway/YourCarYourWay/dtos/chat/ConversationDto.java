package me.yourcaryourway.YourCarYourWay.dtos.chat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.yourcaryourway.YourCarYourWay.dtos.user.ConversationUserDto;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConversationDto {
    private ConversationUserDto sender;
    private ConversationUserDto receiver;
    private String subject;
    private List<MessageDto> messages;
}
