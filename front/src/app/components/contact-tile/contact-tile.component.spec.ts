import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ContactTileComponent } from './contact-tile.component';

describe('ContactTileComponent', () => {
  let component: ContactTileComponent;
  let fixture: ComponentFixture<ContactTileComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ContactTileComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ContactTileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
