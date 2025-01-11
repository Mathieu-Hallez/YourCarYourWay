import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ConversationDto } from '../../../interfaces/ConversationDto';
import { ContactDto } from '../../../interfaces/ContactDto';

@Injectable({
  providedIn: 'root'
})
export class ChatService {

  private pathService : string = '/api/chat';

  constructor(
    private httpClient : HttpClient
  ) { }

  public getConversation(senderEmail : string, receiverEmail : string) : Observable<ConversationDto> {
    return this.httpClient.get<ConversationDto>(`${this.pathService}/conversation?senderEmail=${senderEmail}&receiverEmail=${receiverEmail}`);
  }

  public getContacts() : Observable<Array<ContactDto>> {
    return this.httpClient.get<Array<ContactDto>>(`${this.pathService}/contacts`);
  }

}
