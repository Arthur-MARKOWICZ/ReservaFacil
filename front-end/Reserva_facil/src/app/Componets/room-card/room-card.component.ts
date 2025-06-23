import {  Component, Input, Output, EventEmitter } from '@angular/core';
import { Room } from '../room-component/room.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-room-card',
  imports: [CommonModule],
  templateUrl: './room-card.component.html',
  styleUrl: './room-card.component.css'
})
export class RoomCardComponent {
@Input() room!: Room;
  @Output() reservar = new EventEmitter<string>();

  reservarSala() {
    this.reservar.emit(this.room.id);
  }
}
