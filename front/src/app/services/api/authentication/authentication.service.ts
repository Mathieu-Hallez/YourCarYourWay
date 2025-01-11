import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { TokenDto } from '../../../interfaces/TokenDto';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  private pathService : string = '/api/authentication';

  constructor(
    private httpClient : HttpClient
  ) { }

  public login(email : string, password : string) : Observable<TokenDto> {
    return this.httpClient.post<TokenDto>(`${this.pathService}/login`, {email: email, password: password});
  }
}
