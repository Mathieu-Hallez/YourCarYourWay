import { Component, inject, Input } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { WebsocketService } from '../../services/websocket/websocket.service';
import { LocalStorageService } from '../../services/local-storage/local-storage.service';

@Component({
  selector: 'app-message-text-input',
  imports: [FormsModule, MatFormFieldModule, MatInputModule],
  templateUrl: './message-text-input.component.html',
  styleUrl: './message-text-input.component.scss'
})
export class MessageTextInputComponent {

  private webSocketService = inject(WebsocketService);
  private localStorageService = inject(LocalStorageService);

  @Input({ required: true })
  receiver! : string;

  @Input()
  lastMessageId! : number | undefined;

  formObject : any = {
    message : ''
  }

  onSendMessage(): void {
    this.webSocketService.sendMessage(this.formObject.message, this.localStorageService.get<string>('receiverSelected') ?? undefined, this.lastMessageId);
  }

}
