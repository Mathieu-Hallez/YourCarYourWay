import { Component, inject } from '@angular/core';
import { ConversationComponent } from "../../components/conversation/conversation.component";
import { ContactListComponent } from "../../components/contact-list/contact-list.component";
import { SessionService } from '../../services/session/session.service';

@Component({
  selector: 'app-chat',
  imports: [ConversationComponent, ContactListComponent],
  templateUrl: './chat.component.html',
  styleUrl: './chat.component.scss'
})
export class ChatComponent {
  private sessionService = inject(SessionService);

  public receiverEmail! : string;
  public senderEmail : string = this.sessionService.session?.$email ?? '';

  public selectConversation(email : string): void {
    this.receiverEmail = email;
  }
}
