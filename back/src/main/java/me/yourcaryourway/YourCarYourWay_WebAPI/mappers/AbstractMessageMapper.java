package me.yourcaryourway.YourCarYourWay_WebAPI.mappers;

import me.yourcaryourway.YourCarYourWay_WebAPI.dtos.chat.AbstractMessageDto;
import me.yourcaryourway.YourCarYourWay_WebAPI.dtos.chat.CreateMessageDto;
import me.yourcaryourway.YourCarYourWay_WebAPI.dtos.chat.MessageDto;
import me.yourcaryourway.YourCarYourWay_WebAPI.dtos.chat.SaveMessageDto;
import me.yourcaryourway.YourCarYourWay_WebAPI.models.Message;
import me.yourcaryourway.YourCarYourWay_WebAPI.services.MessageService;
import me.yourcaryourway.YourCarYourWay_WebAPI.services.UserService;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.format.DateTimeFormatter;

@Component
@Mapper(
        componentModel = "spring",
        uses = {
                MessageService.class,
                UserService.class
        },
        imports = {
                DateTimeFormatter.class,
                Timestamp.class,
                Instant.class
        },
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ON_IMPLICIT_CONVERSION
)
public abstract class AbstractMessageMapper implements EntityMapper<AbstractMessageDto, Message>{

    @Autowired
    MessageService messageService;

    @Autowired
    UserService userService;

    @Override
    @Mappings({
            @Mapping(target = "createdAt", expression = "java(DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(message.getCreatedAt().toLocalDateTime()))"),
            @Mapping(target = "type", expression = "java(message.getType().getName())"),
            @Mapping(target = "senderEmail", expression = "java(message.getSender().getEmail())"),
            @Mapping(target = "receiverEmail", expression = "java(message.getReceiver().getEmail())")
    })
    public abstract MessageDto toDto(Message message);

    @Mappings({
            @Mapping(target = "createdAt", expression = "java(messageDto.getCreatedAt() == null ? Timestamp.from(Instant.now()) : Timestamp.valueOf(messageDto.getCreatedAt()))"),
            @Mapping(target = "isRead", expression = "java(messageDto.getIsRead() == null ? false : messageDto.getIsRead())"),
            @Mapping(target = "type", expression = "java(this.messageService.getType(messageDto.getType()))"),
            @Mapping(target = "sender", expression = "java(this.userService.getUser(messageDto.getSenderEmail()))"),
            @Mapping(target = "receiver", expression = "java(this.userService.getUser(messageDto.getReceiverEmail()))")
    })
    public abstract Message toEntity(MessageDto messageDto);

    @Mappings({
            @Mapping(target = "createdAt", expression = "java(Timestamp.from(Instant.now()))"),
            @Mapping(target = "parent", expression = "java(saveMessageDto.getParentMessageId() != null ? this.messageService.getMessage(saveMessageDto.getParentMessageId()) : null)"),
            @Mapping(target = "isRead", expression = "java(false)"),
            @Mapping(target = "type", expression = "java(this.messageService.getType(saveMessageDto.getType()))"),
            @Mapping(target = "sender", expression = "java(this.userService.getUser(saveMessageDto.getSenderEmail()))"),
            @Mapping(target = "receiver", expression = "java(this.userService.getUser(saveMessageDto.getReceiverEmail()))"),
    })
    public abstract Message toEntity(SaveMessageDto saveMessageDto);

    @Mappings({
            @Mapping(target = "type", expression = "java(this.messageService.getType(createMessageDto.getType()))"),
            @Mapping(target = "sender", expression = "java(this.userService.getUser(createMessageDto.getSenderEmail()))"),
            @Mapping(target = "receiver", expression = "java(this.userService.getUser(createMessageDto.getReceiverEmail()))"),
    })
    public abstract Message toEntity(CreateMessageDto createMessageDto);
}
