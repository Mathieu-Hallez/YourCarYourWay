import { Component, inject, Input } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, Validators } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';

@Component({
  selector: 'app-message-text-input',
  imports: [FormsModule, MatFormFieldModule, MatInputModule],
  templateUrl: './message-text-input.component.html',
  styleUrl: './message-text-input.component.scss'
})
export class MessageTextInputComponent {

  @Input({ required: true })
  receiver! : string;

  formObject : any = {
    message : ''
  }

  onSendMessage(): void {
    const formValue = this.formObject;
    // TODO Send message to Websocket
  }

}
