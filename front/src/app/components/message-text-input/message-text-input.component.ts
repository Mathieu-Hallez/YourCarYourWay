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

  private formBuilder = inject(FormBuilder);

  formObject : any = {
    message : ''
  }
  
  messageForm : FormGroup = this.formBuilder.group({
    content: [
      '',
      [
        Validators.required
      ]
    ]
  });

  onSendMessage(): void {
    const formValue = this.formObject;
    // TODO Send message to Websocket
  }

}
