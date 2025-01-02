import { Injectable } from '@angular/core';
import SockJS from 'sockjs-client';
import * as Stomp from 'stompjs';

@Injectable({
  providedIn: 'root'
})
export class WebsocketService {

  private socketClient! : Stomp.Client;

  constructor() {
  }

  onConnect() {
    let ws = new SockJS('https://localhost:3002/ws');
    this.socketClient = Stomp.over(ws);
    this.socketClient.connect({}, () => {
      console.log("Connecting to WS...")
    })
  }
}
