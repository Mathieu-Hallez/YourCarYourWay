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

  receiverEmail! : string;
  senderEmail : string = this.sessionService.session?.$email ?? '';

  selectConversation(email : string): void {
    this.receiverEmail = email;
  }
}
