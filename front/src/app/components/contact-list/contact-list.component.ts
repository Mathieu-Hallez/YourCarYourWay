import { Component, inject, OnDestroy, OnInit, output, OutputEmitterRef } from '@angular/core';
import { Contact } from '../../models/Contact';
import { ContactTileComponent } from "../contact-tile/contact-tile.component";
import { Subject, takeUntil } from 'rxjs';
import { ChatService } from '../../services/api/chat/chat.service';
import { ContactDto } from '../../interfaces/ContactDto';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-contact-list',
  imports: [ContactTileComponent, CommonModule],
  templateUrl: './contact-list.component.html',
  styleUrl: './contact-list.component.scss'
})
export class ContactListComponent implements OnInit, OnDestroy {

    private chatService = inject(ChatService);

    private destroy$ : Subject<boolean> = new Subject<boolean>();

    contacts : Array<Contact> = [];

    onSelectConversation : OutputEmitterRef<string> = output<string>();

    ngOnInit(): void {
        this.chatService.getContacts().pipe(
            takeUntil(this.destroy$)
        ).subscribe({
            next: (contactsDto) => this.fetchContacts(contactsDto)
        })
    }

    ngOnDestroy(): void {
        this.destroy$.next(true);
    }

    private fetchContacts(contactsDto : Array<ContactDto>) {
        this.contacts = contactsDto.map((contactDto) => {
            return new Contact(contactDto.firstname, contactDto.lastname, contactDto.email)
        });
    }

    selectConversation(email : string): void {
        this.onSelectConversation.emit(email);
    }
}
