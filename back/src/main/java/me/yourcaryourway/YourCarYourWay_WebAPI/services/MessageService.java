package me.yourcaryourway.YourCarYourWay_WebAPI.services;

import me.yourcaryourway.YourCarYourWay_WebAPI.models.Message;
import me.yourcaryourway.YourCarYourWay_WebAPI.models.MessageType;
import me.yourcaryourway.YourCarYourWay_WebAPI.models.User;
import me.yourcaryourway.YourCarYourWay_WebAPI.repositories.MessageRepository;
import me.yourcaryourway.YourCarYourWay_WebAPI.repositories.MessageTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private MessageTypeRepository messageTypeRepository;

    public Message getLastMessage(final User user1, final User user2) {
        Message messageUser1ToUser2 = this.messageRepository.findFirstBySenderIdAndReceiverIdOrderByCreatedAtDesc(user1.getId(), user2.getId()).orElse(null);
        Message messageUser2ToUser1 = this.messageRepository.findFirstBySenderIdAndReceiverIdOrderByCreatedAtDesc(user2.getId(), user1.getId()).orElse(null);
        if(messageUser2ToUser1 == null && messageUser1ToUser2 == null) {
            return null;
        }
        if(messageUser1ToUser2 == null) {
            return messageUser2ToUser1;
        }
        if(messageUser2ToUser1 == null) {
            return messageUser1ToUser2;
        }
        if(messageUser2ToUser1.getCreatedAt().after(messageUser1ToUser2.getCreatedAt())) {
            return messageUser2ToUser1;
        } else {
            return messageUser1ToUser2;
        }
    }

    public Message saveMessage(Message message) {
        return this.messageRepository.save(message);
    }

    public Message getMessage(Long id) {
        return this.messageRepository.findById(id).orElse(null);
    }

    public MessageType getType(String name) {
        return this.messageTypeRepository.findByName(name).orElse(null);
    }
}
