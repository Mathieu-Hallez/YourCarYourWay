package me.yourcaryourway.chat_server.controllers;

import lombok.RequiredArgsConstructor;
import me.yourcaryourway.chat_server.models.ChatMessage;
import me.yourcaryourway.chat_server.models.ChatNotification;
import me.yourcaryourway.chat_server.models.MessageType;
import me.yourcaryourway.chat_server.models.api.CreateConversationDto;
import me.yourcaryourway.chat_server.models.api.CreateMessageDto;
import me.yourcaryourway.chat_server.models.api.MessageDto;
import me.yourcaryourway.chat_server.models.api.SaveMessageDto;
import me.yourcaryourway.chat_server.services.ChatMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ChatController {

    @Autowired
    private ChatMessageService chatMessageService;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/chat")
    public void processMessage(@Payload ChatMessage chatMessage) {
        MessageDto lastMessage = this.chatMessageService.getLastMessage(chatMessage.getSenderEmail(), chatMessage.getReceiverEmail());

        MessageDto messageDto;
        if(lastMessage != null) {
            messageDto = this.chatMessageService.save(
                SaveMessageDto.builder()
                    .parentMessageId(lastMessage.getId())
                    .text(chatMessage.getText())
                    .type("CHAT")
                    .senderEmail(chatMessage.getSenderEmail())
                    .receiverEmail(chatMessage.getReceiverEmail())
                    .build()
            );
        } else {
            messageDto = this.chatMessageService.createConversation(
                CreateConversationDto.builder()
                    .subject("SupportChat")
                    .messageDto(CreateMessageDto.builder()
                        .type("CHAT")
                        .text(chatMessage.getText())
                        .senderEmail(chatMessage.getSenderEmail())
                        .receiverEmail(chatMessage.getReceiverEmail())
                        .build()
                    )
                    .build()
            );
        }

        ChatNotification chatNotification = ChatNotification.builder()
                .id(messageDto.getId())
                .parent(messageDto.getParentMessageId())
                .text(messageDto.getText())
                .isRead(messageDto.getIsRead())
                .sender(messageDto.getSenderEmail())
                .receiver(messageDto.getReceiverEmail())
                .type(MessageType.valueOf(messageDto.getType()))
                .createdAt(messageDto.getCreatedAt())
                .build();

        this.simpMessagingTemplate.convertAndSendToUser(
                chatMessage.getReceiverEmail(),
                "/queue/messages",
                chatNotification
        );
        this.simpMessagingTemplate.convertAndSendToUser(
                chatMessage.getSenderEmail(),
                "/queue/messages",
                chatNotification
        );
    }
}
