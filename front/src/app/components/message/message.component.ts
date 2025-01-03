import { Component, Input } from '@angular/core';
import { Message } from '../../models/Message';

@Component({
  selector: 'app-message',
  imports: [],
  templateUrl: './message.component.html',
  styleUrl: './message.component.scss'
})
export class MessageComponent {

  @Input() message! : Message;

  isReadDisplay : string = this.message?.$isRead ? '✔️' : '✖️';

}
