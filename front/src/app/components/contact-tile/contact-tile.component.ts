import { Component, inject, Input, output, Output, OutputEmitterRef } from '@angular/core';
import { Contact } from '../../models/Contact';
import { LocalStorageService } from '../../services/local-storage/local-storage.service';

@Component({
  selector: 'app-contact-tile',
  imports: [],
  templateUrl: './contact-tile.component.html',
  styleUrl: './contact-tile.component.scss'
})
export class ContactTileComponent {

  private localStorageService = inject(LocalStorageService);

  @Input()
  contact! : Contact;

  onContactUpdate : OutputEmitterRef<string> = output<string>();

  onClick(): void {
    this.onContactUpdate.emit(this.contact.$email);
    this.localStorageService.set('receiverSelected', this.contact.$email);
  }
}
