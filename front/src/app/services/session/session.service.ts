import { inject, Injectable } from '@angular/core';
import { Session } from '../../models/Session';
import { AuthenticationService } from '../api/authentication/authentication.service';
import { map, Observable, take } from 'rxjs';
import { TokenDto } from '../../interfaces/TokenDto';

@Injectable({
  providedIn: 'root'
})
export class SessionService {
  private authenticationService : AuthenticationService = inject(AuthenticationService);

  public session : Session | null = null;

  constructor() { }

  public signIn(email : string, password : string): Observable<boolean> {
    return this.authenticationService.login(email, password)
      .pipe(
        take(1),
        map((tokenDto : TokenDto) => {
          this.session = new Session(email, tokenDto.token);
          return tokenDto != null;
        })
      );
  }

  public signOut(): void {
    this.session = null;
  }

  public isLogged(): boolean {
    return this.session != null && this.session.$token != null;
  }
}
