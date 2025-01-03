package me.yourcaryourway.chat_server.controllers;

import lombok.RequiredArgsConstructor;
import me.yourcaryourway.chat_server.models.ChatMessage;
import me.yourcaryourway.chat_server.models.ChatNotification;
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
        System.out.println("Chat message to send: " + chatMessage.toString());
        //TODO
        //Récuperer le dernier message entre sender et receiver
        // SI existe donne son id à parentID
        // Sinon créer une conversation
        ChatMessage savedMessage = this.chatMessageService.save(chatMessage);
        ChatNotification chatNotification = ChatNotification.builder()
                .id(savedMessage.getId().toString())
                .senderEmail(savedMessage.getSenderEmail())
                .receiverEmail(savedMessage.getReceiverEmail())
                .content(savedMessage.getText())
                .build();

        this.simpMessagingTemplate.convertAndSendToUser(
                chatMessage.getReceiverEmail(),
                "/queue/messages",
                chatNotification
        );
    }
}
