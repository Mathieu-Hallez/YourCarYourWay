import { Component, ElementRef, inject, Input, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { ChatService } from '../../services/api/chat/chat.service';
import { BehaviorSubject, catchError, of, Subject, switchMap, takeUntil } from 'rxjs';
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

    @Input({required: true}) set receiverEmail(email : string) {
        this.receiverEmail$.next(email);
    }

    private receiverEmail$ = new BehaviorSubject<string | null>(null);

    private destroy$ : Subject<boolean> = new Subject<boolean>();

    messages : Array<Message> = [];
    receiver! : ConversationUserDto;
    subject : string | null = null;
    

    ngOnInit(): void {
        this.receiverEmail$.pipe(
            switchMap((email) => {
              if (!email) {
                return of(null); // Retourner une liste vide si email est nul
              }
              return this.chatService.getConversation(this.senderEmail, email).pipe(
                catchError((error) => {
                  console.error('Erreur lors du chargement des messages', error);
                  return of(null);
                })
              );
            }),
            takeUntil(this.destroy$)
        ).subscribe((conversationDto : ConversationDto | null) => {
            if(conversationDto)
                this.fetchConversation(conversationDto);
            else
                this.messages = [];
        });

        this.webSocketService.connect();
        this.webSocketService.receivedMessage$().subscribe({
            next: (message) => {
                if(message) {
                    this.messages.push(message);
                }
            },
            error: (err) => {console.error(err)}
        });
    }
    ngOnDestroy(): void {
        this.destroy$.next(true);
        this.webSocketService.disconnect();
    }

    public fetchConversation(conversationDto : ConversationDto) {
        this.receiver = conversationDto.receiver;
        this.subject = conversationDto.subject ?? null;

        this.messages = conversationDto.messages.map((messageDto) => {
            return new Message(messageDto.id, messageDto.parent_id, messageDto.text, messageDto.is_read, messageDto.sender_email, messageDto.receiver_email, dayjs(messageDto.created_at).format("DD/MM/YYYY HH:mm"));
        });
        this.messages.sort((a, b) => {
            return dayjs(a.$createdAt).isBefore(b.$createdAt) ? -1 : 1;
        })      
    }

    private sendMessage(message : string) {
        const receiverEmail = this.receiverEmail$.getValue();
        if(receiverEmail == null) {
            console.error("No receiver selected.");
            return;
        }
        this.webSocketService.sendMessage(message, receiverEmail);
    }
    sendMessageBind = this.sendMessage.bind(this);
}

