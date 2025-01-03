package me.yourcaryourway.YourCarYourWay.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import me.yourcaryourway.YourCarYourWay.dtos.ApiResponseDto;
import me.yourcaryourway.YourCarYourWay.dtos.api.ErrorDto;
import me.yourcaryourway.YourCarYourWay.dtos.api.SuccessResponseDto;
import me.yourcaryourway.YourCarYourWay.dtos.chat.*;
import me.yourcaryourway.YourCarYourWay.dtos.user.ConversationUserDto;
import me.yourcaryourway.YourCarYourWay.mappers.AbstractConversationUserMapper;
import me.yourcaryourway.YourCarYourWay.mappers.AbstractMessageMapper;
import me.yourcaryourway.YourCarYourWay.models.Message;
import me.yourcaryourway.YourCarYourWay.models.User;
import me.yourcaryourway.YourCarYourWay.services.MessageService;
import me.yourcaryourway.YourCarYourWay.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/chat")
@Tag(name = "Chat", description = "The chat API. Contains all the operations that can be performed for textual chat application.")
public class ChatController {

    Logger logger = LoggerFactory.getLogger(ChatController.class);

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    @Autowired
    private AbstractConversationUserMapper abstractConversationUserMapper;

    @Autowired
    private AbstractMessageMapper abstractMessageMapper;

    @PostMapping("/message/save")
    public ResponseEntity<ApiResponseDto> saveMessage(@RequestBody final SaveMessageDto saveMessageDto) {
        try {
            if(saveMessageDto.getParentMessageId() == null) {
                return new ResponseEntity<>(new ErrorDto("Message hasn't message parent"), HttpStatus.INTERNAL_SERVER_ERROR);
            }
            Message messageToSave = this.abstractMessageMapper.toEntity(saveMessageDto);
            if(messageToSave == null) {
                return new ResponseEntity<>(new ErrorDto("No message to save."), HttpStatus.NOT_FOUND);
            }
            messageToSave.setSubject(Objects.requireNonNullElse(messageToSave.getParent(), null).getSubject());

            return ResponseEntity.ok(this.abstractMessageMapper.toDto(this.messageService.saveMessage(messageToSave)));
        } catch(Exception exception) {
            logger.debug(Arrays.toString(exception.getStackTrace()));
            logger.error(exception.getMessage());
            System.out.println(exception.getMessage());
            System.out.println(Arrays.toString(exception.getStackTrace()));
            return ResponseEntity.internalServerError().body(new ErrorDto("Error: Internal server error."));
        }
    }

    @PutMapping("/message/{id}/read")
    public ResponseEntity<?> readAMessage(@PathVariable("id") final Long id) {
        try {
            Message message = this.messageService.getMessage(id);
            if (message == null) {
                return new ResponseEntity<>(new ErrorDto("No message found with id " + id + "."), HttpStatus.NOT_FOUND);
            }
            message.setIsRead(true);
            this.messageService.saveMessage(message);

        } catch(Exception exception) {
            logger.debug(Arrays.toString(exception.getStackTrace()));
            logger.error(exception.getMessage());
            System.out.println(exception.getMessage());
            System.out.println(Arrays.toString(exception.getStackTrace()));
            return ResponseEntity.internalServerError().body(new ErrorDto("Error: Internal server error."));
        }
        return ResponseEntity.ok(new SuccessResponseDto("Message successfully read."));
    }

    @GetMapping("/conversation")
    public ResponseEntity<?> getConversation(@RequestParam final String senderEmail, @RequestParam final String receiverEmail) {
        User sender = this.userService.getUser(senderEmail);
        User receiver = this.userService.getUser(receiverEmail);
        if(sender == null || receiver == null) {
            return new ResponseEntity<>(new ErrorDto("No sender or receiver found."), HttpStatus.NOT_FOUND);
        }
        Message lastMessage = this.messageService.getLastMessage(sender, receiver);
        if(lastMessage == null) {
            return new ResponseEntity<>(new ErrorDto("No message found."), HttpStatus.NOT_FOUND);
        }

        List<MessageDto> messageDtos = new ArrayList<>();
        messageDtos.add(this.abstractMessageMapper.toDto(lastMessage));
        Message message = lastMessage;
        while(message != null) {
            message = message.getParent();
            if(message != null) {
                messageDtos.add(this.abstractMessageMapper.toDto(message));
            }
        }

        return new ResponseEntity<ConversationDto>(
            new ConversationDto(
                this.abstractConversationUserMapper.toDto(sender),
                this.abstractConversationUserMapper.toDto(receiver),
                lastMessage.getSubject(),
                messageDtos
            ),
            HttpStatus.OK
        );
    }

    @PostMapping("/conversation/create")
    public ResponseEntity<ApiResponseDto> createConversation(@RequestBody final CreateConversationDto createConversationDto) {
        try{
            MessageDto messageDto = createConversationDto.getMessageDto();
            User sender = this.userService.getUser(messageDto.getSenderEmail());
            User receiver = this.userService.getUser(messageDto.getReceiverEmail());
            if(sender == null || receiver == null) {
                return new ResponseEntity<  >(new ErrorDto("No sender or receiver found."), HttpStatus.NOT_FOUND);
            }
            Message message = this.abstractMessageMapper.toEntity(createConversationDto.getMessageDto());
            message.setSubject(createConversationDto.getSubject());
            message.setSender(sender);
            message.setReceiver(receiver);

            this.messageService.saveMessage(message);
        } catch(Exception exception) {
            logger.debug(Arrays.toString(exception.getStackTrace()));
            logger.error(exception.getMessage());
            System.out.println(exception.getMessage());
            System.out.println(Arrays.toString(exception.getStackTrace()));
            return ResponseEntity.internalServerError().body(new ErrorDto("Error: Internal server error."));
        }
        return new ResponseEntity<>(new SuccessResponseDto("Conversation successfully created."), HttpStatus.CREATED);
    }

    @GetMapping("/contacts")
    public ResponseEntity<?> getContacts() {
        try{
            List<User> users = this.userService.getUsers();
            return new ResponseEntity<>(this.abstractConversationUserMapper.toDtos(users), HttpStatus.OK);
        } catch(Exception exception) {
            logger.debug(Arrays.toString(exception.getStackTrace()));
            logger.error(exception.getMessage());
            System.out.println(exception.getMessage());
            System.out.println(Arrays.toString(exception.getStackTrace()));
            return ResponseEntity.internalServerError().body(new ErrorDto("Error: Internal server error."));
        }
    }
}
