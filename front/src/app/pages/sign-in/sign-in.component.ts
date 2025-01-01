import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';

@Component({
  selector: 'app-sign-in',
  imports: [FormsModule, MatFormFieldModule, MatInputModule],
  templateUrl: './sign-in.component.html',
  styleUrl: './sign-in.component.scss'
})
export class SignInComponent {

    formObject : any = {
        email: '',
        password: ''
    };

    onSignIn(): void {
        const formValue = this.formObject;
        console.log("Submit: " + JSON.stringify(formValue));
    }
}
