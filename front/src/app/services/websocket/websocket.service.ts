import { inject, Injectable } from '@angular/core';
import SockJS from 'sockjs-client';
import { Client } from '@stomp/stompjs';
import { SessionService } from '../session/session.service';
import { BehaviorSubject, Observable } from 'rxjs';
import { Message } from '../../models/Message';
import { NotificationMessage } from '../../interfaces/websocket/NotificationMessage';
import { ChatMessage } from '../../interfaces/websocket/ChatMessage';
import dayjs from 'dayjs';

@Injectable({
  providedIn: 'root',
})
export class WebsocketService {

  private sessionService = inject(SessionService);

  private stompClient! : Client;

  private $receivedMessageSubject: BehaviorSubject<Message | undefined> = new BehaviorSubject<Message | undefined>(undefined);

  constructor() {
    this.stompClient = new Client({
      brokerURL: 'WS://localhost:8080/ws',
      reconnectDelay: 5000,
      webSocketFactory: () => {return new SockJS('http://localhost:8080/ws')},
      onConnect: (frame) => {
        console.log('Connected: ' + frame);
        const onMessageReceivedBind = this.onMessageReceived.bind(this);
        this.stompClient.subscribe(`/user/${this.sessionService.session?.$email}/queue/messages`, onMessageReceivedBind);
      },
      onStompError: (frame) => {
        console.error('Error: ' + frame);
      },
      debug: (str) => {
        console.log(str);
      }
    })
  }

  receivedMessage$(): Observable<Message | undefined> {
    return this.$receivedMessageSubject.asObservable();
  }

  private onMessageReceived(payload : any) {
    const message : NotificationMessage = JSON.parse(payload.body);
    this.$receivedMessageSubject.next(new Message(message.id, message.parent, message.text, message.is_read, message.sender, message.receiver, dayjs(message.created_at).format("DD/MM/YYYY HH:mm")));
  }

  sendMessage(message : string, receiverEmail : string) {
    if(!this.stompClient?.connected || !this.sessionService.isLogged || !receiverEmail || !this.sessionService.session) return;

    const chatMessage : ChatMessage = {
      text: message,
      senderEmail: this.sessionService.session.$email,
      receiverEmail: receiverEmail,
      type: 'CHAT'
    };

    this.stompClient?.publish({
      destination:'/app/chat',
      body: JSON.stringify(chatMessage)
    });
  }

  connect(): void {
    this.stompClient.activate();
  }

  disconnect(): void {
    if(this.stompClient.connected) {
      this.stompClient.deactivate();
    }
  }
}
