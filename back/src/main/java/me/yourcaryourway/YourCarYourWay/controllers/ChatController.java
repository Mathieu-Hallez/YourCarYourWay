package me.yourcaryourway.YourCarYourWay.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import me.yourcaryourway.YourCarYourWay.dtos.chat.CreateConversationDto;
import me.yourcaryourway.YourCarYourWay.dtos.chat.SaveMessageDto;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/Chat")
@Tag(name = "Chat", description = "The chat API. Contains all the operations that can be performed for textual chat application.")
public class ChatController {

    @PostMapping("/saveMessage")
    public ResponseEntity<?> saveMessage(@RequestBody final SaveMessageDto saveMessageDto) {
        throw new NotImplementedException();
    }

    @GetMapping("/conversation/{id}")
    public ResponseEntity<?> getConversation(@PathVariable("id") final Long id) {
        throw new NotImplementedException();
    }

    @PostMapping("/conversation/create")
    public ResponseEntity<?> createConversation(@RequestBody final CreateConversationDto createConversationDto) {
        throw new NotImplementedException();
    }
}
