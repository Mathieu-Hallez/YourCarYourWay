package me.yourcaryourway.chat_server.services;

import me.yourcaryourway.chat_server.dtos.CreateConversationDto;
import me.yourcaryourway.chat_server.dtos.MessageDto;
import me.yourcaryourway.chat_server.dtos.SaveMessageDto;
import me.yourcaryourway.chat_server.configurations.properties.ApiConfigProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class ChatMessageService {

    @Autowired
    private ApiConfigProperties apiConfigProperties;

    private final String controller = "/chat";
    private final RestTemplate restTemplate = new RestTemplate();

    public MessageDto save(SaveMessageDto saveMessageDto) {
        HttpEntity<SaveMessageDto> request = new HttpEntity<>(saveMessageDto);
        ResponseEntity<MessageDto> responseEntity = this.restTemplate.exchange(
                this.apiConfigProperties.baseUrl() + controller + "/message/save",
                HttpMethod.POST,
                request,
                MessageDto.class

        );
        return responseEntity.getBody();
    }

    public MessageDto getLastMessage(String senderEmail, String receiverEmail) {
        try {
            String urlTemplate = UriComponentsBuilder.fromUriString(this.apiConfigProperties.baseUrl() + controller + "/message/last")
                    .queryParam("senderEmail", senderEmail)
                    .queryParam("receiverEmail", receiverEmail)
                    .encode().toUriString();
            ResponseEntity<MessageDto> responseEntity = this.restTemplate.getForEntity(
                    urlTemplate,
                    MessageDto.class
            );

            return responseEntity.getBody();
        } catch (HttpStatusCodeException httpStatusCodeException) {
            if(httpStatusCodeException.getStatusCode().value() == 404) {
                return null;
            }
            throw httpStatusCodeException;
        }
    }

    public MessageDto createConversation(CreateConversationDto createConversationDto) {
        HttpEntity<CreateConversationDto> request = new HttpEntity<>(createConversationDto);
        ResponseEntity<MessageDto> responseEntity = this.restTemplate.exchange(
            this.apiConfigProperties.baseUrl() + controller + "/conversation/create",
                HttpMethod.POST,
                request,
                MessageDto.class
        );

        return responseEntity.getBody();
    }
}
