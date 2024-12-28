package me.yourcaryourway.chat_server.controllers;

import me.yourcaryourway.chat_server.models.ChatMessage;
import me.yourcaryourway.chat_server.models.ChatNotification;
import me.yourcaryourway.chat_server.services.ChatMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    @Autowired
    private ChatMessageService chatMessageService;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/chat")
    public void processMessage(@Payload ChatMessage chatMessage) {
        ChatMessage savedMessage = this.chatMessageService.save(chatMessage);

        this.simpMessagingTemplate.convertAndSendToUser(
                chatMessage.getReceiverEmail(),
                "/queue/messages",
                ChatNotification.builder()
                        .id(savedMessage.getId().toString())
                        .senderEmail(savedMessage.getSenderEmail())
                        .receiverEmail(savedMessage.getReceiverEmail())
                        .content(savedMessage.getText())
                        .build()
        );
    }
}
