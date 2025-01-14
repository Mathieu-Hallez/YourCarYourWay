import { Component, Input } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';
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
  sendMessage! : (message : string) => void;

  public formObject : any = {
    message : ''
  }

  public onSendMessage(form : NgForm): void {
    this.sendMessage(this.formObject.message);
    form.reset({
      message: ''
    });
  }
}
