package me.yourcaryourway.chat_server.services;

import jdk.jshell.spi.ExecutionControl;
import me.yourcaryourway.chat_server.models.ChatMessage;
import me.yourcaryourway.chat_server.models.api.SaveMessageDto;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

@Service
public class ChatMessageService {

    private final String apiUrl = "http://localhost:3001/api/chat";

    public ChatMessage save(ChatMessage chatMessage) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<SaveMessageDto> request = new HttpEntity<>(
                SaveMessageDto.builder()
                    .id(chatMessage.getId())
                    .parentMessageId(chatMessage.getParentId())
                    .type("CHAT")
                    .text(chatMessage.getText())
                    .senderEmail(chatMessage.getSenderEmail())
                    .receiverEmail(chatMessage.getReceiverEmail())
                    .build()
        );
        ResponseEntity<SaveMessageDto> responseEntity = restTemplate.exchange(
                apiUrl + "/message/save",
                HttpMethod.POST,
                request,
                SaveMessageDto.class

        );
        SaveMessageDto saveMessageDto = responseEntity.getBody();
        return ChatMessage.builder()
                .id(saveMessageDto.getId())
                .isRead(false)
                .parentId(saveMessageDto.getParentMessageId())
                .senderEmail(saveMessageDto.getSenderEmail())
                .receiverEmail(saveMessageDto.getReceiverEmail())
                .text(saveMessageDto.getText())
                .isRead(saveMessageDto.getIsRead())
                .build();
    }
}
