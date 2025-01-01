import { Injectable } from '@angular/core';
import { Session } from '../models/Session';

@Injectable({
  providedIn: 'root'
})
export class SessionService {

  private session : Session | null = null;

  constructor() { }

  public signIn(email : string) {
    this.session = new Session(email);
  }

  public isLogged() : boolean {
    return this.session != null
  }
}
