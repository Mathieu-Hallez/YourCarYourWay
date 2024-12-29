import { Component, inject } from '@angular/core';
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
  private formBuilder = inject(FormBuilder)
  
  messageForm : FormGroup = this.formBuilder.group({
    content: [
      '',
      [
        Validators.required
      ]
    ]
  });

  onSendMessage(): void {
    // TODO Send message to Websocket
  }

}
