import { Component, inject, Input, OnInit } from '@angular/core';
import { Message } from '../../models/Message';
import { SessionService } from '../../services/session/session.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-message',
  imports: [CommonModule],
  templateUrl: './message.component.html',
  styleUrl: './message.component.scss'
})
export class MessageComponent implements OnInit {

  private sessionService = inject(SessionService);

  @Input() message! : Message;
  isSent : boolean = false;

  ngOnInit(): void {
    this.isSent = this.sessionService.session?.$email == this.message.$sender;
  }
  
}
