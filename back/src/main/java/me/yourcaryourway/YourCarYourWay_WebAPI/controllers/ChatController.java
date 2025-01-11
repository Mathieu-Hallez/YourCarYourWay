package me.yourcaryourway.YourCarYourWay_WebAPI.controllers;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import me.yourcaryourway.YourCarYourWay_WebAPI.dtos.ApiResponseDto;
import me.yourcaryourway.YourCarYourWay_WebAPI.dtos.api.ErrorDto;
import me.yourcaryourway.YourCarYourWay_WebAPI.dtos.api.SuccessResponseDto;
import me.yourcaryourway.YourCarYourWay_WebAPI.dtos.chat.*;
import me.yourcaryourway.YourCarYourWay_WebAPI.mappers.AbstractConversationUserMapper;
import me.yourcaryourway.YourCarYourWay_WebAPI.mappers.AbstractMessageMapper;
import me.yourcaryourway.YourCarYourWay_WebAPI.models.Message;
import me.yourcaryourway.YourCarYourWay_WebAPI.models.User;
import me.yourcaryourway.YourCarYourWay_WebAPI.services.MessageService;
import me.yourcaryourway.YourCarYourWay_WebAPI.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Message created",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation =  MessageDto.class)
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ErrorDto.class)
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not Found",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ErrorDto.class)
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized access",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ErrorDto.class)
                            )
                    }
            )
    })
    public ResponseEntity<?> saveMessage(@RequestBody final SaveMessageDto saveMessageDto) {
        try {
            if(saveMessageDto.getParentMessageId() == null) {
                return new ResponseEntity<>(new ErrorDto("Message hasn't message parent"), HttpStatus.BAD_REQUEST);
            }

            Message messageToSave = this.abstractMessageMapper.toEntity(saveMessageDto);
            if(messageToSave == null) {
                return new ResponseEntity<>(new ErrorDto("No message to save."), HttpStatus.NOT_FOUND);
            }
            if(messageToSave.getParent() == null) {
                return new ResponseEntity<>(new ErrorDto("Unknown parent message"), HttpStatus.NOT_FOUND);
            }
            messageToSave.setSubject(messageToSave.getParent().getSubject());

            return new ResponseEntity<MessageDto>(this.abstractMessageMapper.toDto(this.messageService.saveMessage(messageToSave)), HttpStatus.CREATED);
        } catch(Exception exception) {
            logger.debug(Arrays.toString(exception.getStackTrace()));
            logger.error(exception.getMessage());
            System.out.println(exception.getMessage());
            System.out.println(Arrays.toString(exception.getStackTrace()));
            return ResponseEntity.internalServerError().body(new ErrorDto("Error: Internal server error."));
        }
    }

    @PutMapping("/message/{id}/read")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully set to read.",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation =  MessageDto.class)
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not Found",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ErrorDto.class)
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized access",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ErrorDto.class)
                            )
                    }
            )
    })
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

    @GetMapping("/message/last")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Last message found",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation =  MessageDto.class)
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not Found",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ErrorDto.class)
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized access",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ErrorDto.class)
                            )
                    }
            )
    })
    public ResponseEntity<?> getLastMessage(@RequestParam final String senderEmail, @RequestParam final String receiverEmail) {
        User sender = this.userService.getUser(senderEmail);
        User receiver = this.userService.getUser(receiverEmail);
        if(sender == null || receiver == null) {
            return new ResponseEntity<>(new ErrorDto("No sender or receiver found."), HttpStatus.NOT_FOUND);
        }
        Message lastMessage = this.messageService.getLastMessage(sender, receiver);
        if(lastMessage == null) {
            return new ResponseEntity<>(new ErrorDto("No message found."), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(this.abstractMessageMapper.toDto(lastMessage), HttpStatus.OK);
    }

    @GetMapping("/conversation")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Conversation found",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation =  MessageDto.class)
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not Found",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ErrorDto.class)
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized access",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ErrorDto.class)
                            )
                    }
            )
    })
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
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Message created",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation =  MessageDto.class)
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not Found",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ErrorDto.class)
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized access",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ErrorDto.class)
                            )
                    }
            )
    })
    public ResponseEntity<ApiResponseDto> createConversation(@RequestBody final CreateConversationDto createConversationDto) {
        try{
            CreateMessageDto messageDto = createConversationDto.getMessageDto();
            User sender = this.userService.getUser(messageDto.getSenderEmail());
            User receiver = this.userService.getUser(messageDto.getReceiverEmail());
            if(sender == null || receiver == null) {
                return new ResponseEntity<  >(new ErrorDto("No sender or receiver found."), HttpStatus.NOT_FOUND);
            }
            Message message = this.abstractMessageMapper.toEntity(createConversationDto.getMessageDto());
            message.setSubject(createConversationDto.getSubject());
            message.setSender(sender);
            message.setReceiver(receiver);
            message.setCreatedAt(Timestamp.from(Instant.now()));
            message.setIsRead(false);

            return new ResponseEntity<>(this.abstractMessageMapper.toDto(this.messageService.saveMessage(message)), HttpStatus.CREATED);
        } catch(Exception exception) {
            logger.debug(Arrays.toString(exception.getStackTrace()));
            logger.error(exception.getMessage());
            System.out.println(exception.getMessage());
            System.out.println(Arrays.toString(exception.getStackTrace()));
            return ResponseEntity.internalServerError().body(new ErrorDto("Error: Internal server error."));
        }
    }

    @GetMapping("/contacts")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "List of user.",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation =  MessageDto.class)
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized access",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ErrorDto.class)
                            )
                    }
            )
    })
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
