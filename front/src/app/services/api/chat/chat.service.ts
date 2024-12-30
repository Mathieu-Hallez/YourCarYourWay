import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ConversationDto } from '../../../interfaces/ConversationDto';

@Injectable({
  providedIn: 'root'
})
export class ChatService {

  private pathService : string = '/api/chat';

  constructor(
    private httpClient : HttpClient
  ) { }

  getConversation(senderEmail : string, receiverEmail : string) : Observable<ConversationDto> {
    return this.httpClient.get<ConversationDto>(`${this.pathService}/conversation?sender=${senderEmail}&receiver=${receiverEmail}`);
  }

}
