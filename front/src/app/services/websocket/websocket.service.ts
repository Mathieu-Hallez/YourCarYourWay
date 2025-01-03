import { inject, Injectable } from '@angular/core';
import SockJS from 'sockjs-client';
import * as Stomp from 'stompjs';
import { SessionService } from '../session/session.service';
import { LocalStorageService } from '../local-storage/local-storage.service';
import { BehaviorSubject, Observable, Subject } from 'rxjs';
import { ChatMessage } from '../../models/ChatMessage';

@Injectable({
  providedIn: 'root'
})
export class WebsocketService {

  private sessionService = inject(SessionService);
  private localStorageService = inject(LocalStorageService);

  private stompClient : Stomp.Client | null = null;

  private $receivedMessageSubject: BehaviorSubject<ChatMessage> = new BehaviorSubject<ChatMessage>(new ChatMessage("","","",""));

  public $receivedMessage(): Observable<ChatMessage> {
    return this.$receivedMessageSubject.asObservable();
  }

  onConnect() {
    let ws = new SockJS('http://localhost:3002/ws');
    this.stompClient = Stomp.over(ws);
    this.stompClient.connect({}, this.onConnected, this.onError)
  }

  private onConnected(): void {
    // console.log('On connecting...');
    this.stompClient?.subscribe(`/user/${this.sessionService.session?.$email}/queue/messages`, this.onMessageReceived);
    this.stompClient?.subscribe(`/user/public`, this.onMessageReceived);

    // register the connected user
    this.stompClient?.send('app/user.addUser',
      {},
      JSON.stringify({
        email: this.sessionService.session?.$email,
        status: 'ONLINE'
      })
    );


  }

  private onError(): void {}

  private onMessageReceived(payload : any) {
    const message = JSON.parse(payload.body);
    if(this.sessionService.session?.$email && this.localStorageService.get<string>('receiverSelected') === message.senderEmail) {
      this.$receivedMessageSubject.next(new ChatMessage(message.text, message.senderEmail, message.receiverEmail, message.type, message.id ?? null, message.parentId ?? null, message.isRead ?? null));
    }
  }

  sendMessage(message : string, receiverEmail? : string) {
    if(!this.stompClient?.connected || !this.sessionService.isLogged || !receiverEmail) return;

    const chatMessage = {
      text: message,
      senderEmail: this.sessionService.session?.$email,
      receiverEmail: receiverEmail,
      type: 'CHAT'
    }

    this.stompClient?.send('/app/chat', {}, JSON.stringify(chatMessage))
  }
}
