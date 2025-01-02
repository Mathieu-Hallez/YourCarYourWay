import { HttpClient } from '@angular/common/http';
import { Component, inject, Input, OnDestroy, OnInit } from '@angular/core';
import { ChatService } from '../../services/api/chat/chat.service';
import { Subject, takeUntil } from 'rxjs';
import { ConversationDto } from '../../interfaces/ConversationDto';
import { Message } from '../../models/Message';
import { ConversationUserDto } from '../../interfaces/ConversationUserDto';
import dayjs from 'dayjs';
import { MessageTextInputComponent } from "../message-text-input/message-text-input.component";
import { MessageComponent } from "../message/message.component";
import { WebsocketService } from '../../services/websocket/websocket.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-conversation',
  imports: [MessageTextInputComponent, MessageComponent, CommonModule],
  templateUrl: './conversation.component.html',
  styleUrl: './conversation.component.scss'
})
export class ConversationComponent implements OnInit, OnDestroy {

    private chatService = inject(ChatService);
    private webSocketService = inject(WebsocketService);

    @Input({required: true})
    senderEmail! : string;

    @Input({required: true})
    receiverEmail! : string;

    private destroy$ : Subject<boolean> = new Subject<boolean>();

    messages : Array<Message> = [];
    receiver! : ConversationUserDto;
    subject : string | null = null;

    ngOnInit(): void {
        this.chatService.getConversation(this.senderEmail, this.receiverEmail).pipe(
            takeUntil(this.destroy$)
        ).subscribe({
            next: conversationDto => this.fetchConversation(conversationDto)
        })
    }

    ngOnDestroy(): void {
        this.destroy$.next(true);
    }

    public fetchConversation(conversationDto : ConversationDto) {
        this.receiver = conversationDto.receiver;
        this.subject = conversationDto.subject ?? null;

        this.messages = conversationDto.messages.map((messageDto) => {
            return new Message(messageDto.id, messageDto.parentId, messageDto.content, messageDto.isRead, messageDto.sender, messageDto.receiver, dayjs(messageDto.createdAt).format("DD/MM/YYYY HH:mm"));
        });

        this.webSocketService.onConnect();
    }
}
