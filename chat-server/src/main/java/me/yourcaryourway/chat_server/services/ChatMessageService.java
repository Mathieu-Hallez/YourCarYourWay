package me.yourcaryourway.chat_server.services;

import me.yourcaryourway.chat_server.models.ChatMessage;
import me.yourcaryourway.chat_server.models.api.CreateConversationDto;
import me.yourcaryourway.chat_server.models.api.MessageDto;
import me.yourcaryourway.chat_server.models.api.SaveMessageDto;
import me.yourcaryourway.chat_server.configurations.properties.ApiConfigProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class ChatMessageService {

    @Autowired
    private ApiConfigProperties apiConfigProperties;

    private final String controller = "/chat";
    private final RestTemplate restTemplate = new RestTemplate();

    public ChatMessage save(ChatMessage chatMessage) {
        HttpEntity<SaveMessageDto> request = new HttpEntity<>(
                SaveMessageDto.builder()
                    .id(chatMessage.getId() != null ? chatMessage.getId() : null)
                    .parentMessageId(chatMessage.getParentId())
                    .type("CHAT")
                    .text(chatMessage.getText())
                    .senderEmail(chatMessage.getSenderEmail())
                    .receiverEmail(chatMessage.getReceiverEmail())
                    .build()
        );
        System.out.println("Save Message Dto: " + request.getBody());
        ResponseEntity<SaveMessageDto> responseEntity = this.restTemplate.exchange(
                this.apiConfigProperties.baseUrl() + controller + "/message/save",
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

    public ChatMessage getLastMessage(String senderEmail, String receiverEmail) {
        try {
            String urlTemplate = UriComponentsBuilder.fromUriString(this.apiConfigProperties.baseUrl() + controller + "/message/last")
                    .queryParam("senderEmail", senderEmail)
                    .queryParam("receiverEmail", receiverEmail)
                    .encode().toUriString();
            ResponseEntity<MessageDto> responseEntity = this.restTemplate.getForEntity(
                    urlTemplate,
                    MessageDto.class
            );

            MessageDto lastMessage = responseEntity.getBody();
            return ChatMessage.builder()
                    .id(lastMessage.getId())
                    .isRead(false)
                    .senderEmail(lastMessage.getSenderEmail())
                    .receiverEmail(lastMessage.getReceiverEmail())
                    .text(lastMessage.getText())
                    .isRead(lastMessage.getIsRead())
                    .build();
        } catch (HttpStatusCodeException httpStatusCodeException) {
            if(httpStatusCodeException.getStatusCode().value() == 404) {
                return null;
            }
            throw httpStatusCodeException;
        }
    }

    public ChatMessage createConversation(ChatMessage chatMessage) {
        HttpEntity<CreateConversationDto> request = new HttpEntity<>(
                CreateConversationDto.builder()
                        .subject(chatMessage.getSubject())
                        .messageDto(MessageDto.builder()
                                .id(0L)
                                .type("CHAT")
                                .text(chatMessage.getText())
                                .senderEmail(chatMessage.getSenderEmail())
                                .receiverEmail(chatMessage.getReceiverEmail())
                                .build()
                        )
                        .build()
        );
        ResponseEntity<MessageDto> responseEntity = this.restTemplate.exchange(
            this.apiConfigProperties.baseUrl() + controller + "/conversation/create",
                HttpMethod.POST,
                request,
                MessageDto.class
        );

        MessageDto savedMessage = responseEntity.getBody();
        return ChatMessage.builder()
                .id(savedMessage.getId())
                .isRead(false)
                .senderEmail(savedMessage.getSenderEmail())
                .receiverEmail(savedMessage.getReceiverEmail())
                .text(savedMessage.getText())
                .isRead(savedMessage.getIsRead())
                .build();
    }
}
