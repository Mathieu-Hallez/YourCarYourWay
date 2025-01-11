import { Component, Input, output, OutputEmitterRef } from '@angular/core';
import { Contact } from '../../models/Contact';

@Component({
  selector: 'app-contact-tile',
  imports: [],
  templateUrl: './contact-tile.component.html',
  styleUrl: './contact-tile.component.scss'
})
export class ContactTileComponent {

  @Input()
  contact! : Contact;

  public onContactUpdate : OutputEmitterRef<string> = output<string>();

  public onClick(): void {
    this.onContactUpdate.emit(this.contact.$email);
  }
}
