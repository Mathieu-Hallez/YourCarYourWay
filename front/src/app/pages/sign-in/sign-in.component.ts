import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { SessionService } from '../../services/session/session.service';
import { Router } from '@angular/router';
import { take } from 'rxjs';

@Component({
  selector: 'app-sign-in',
  imports: [FormsModule, MatFormFieldModule, MatInputModule],
  templateUrl: './sign-in.component.html',
  styleUrl: './sign-in.component.scss'
})
export class SignInComponent {

    private sessionService = inject(SessionService);
    
    public errorMessage : string | null = null;
    public formObject : any = {
        email: '',
        password: ''
    };

    constructor(
        private router : Router
    ) {}

    public onSignIn(): void {
        this.sessionService.signIn(this.formObject.email, this.formObject.password)
            .pipe(
                take(1)
            )
            .subscribe({
                next: (value) => {
                    if(value) {
                        this.router.navigateByUrl('/chat');
                    }
                },
                error: (err) => {
                    this.errorMessage = err.message ?? 'An error occured!'
                }
            });
    }
}
