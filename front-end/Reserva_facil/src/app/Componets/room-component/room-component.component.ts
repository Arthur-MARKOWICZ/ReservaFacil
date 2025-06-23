import { Component, Inject, OnInit, PLATFORM_ID } from '@angular/core';
import { isPlatformBrowser } from '@angular/common';
import { RoomService, Room } from '../room-component/room.service';
import { RoomCardComponent } from "../room-card/room-card.component";
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-room-component',
  templateUrl: './room-component.component.html',
  standalone: true,
  imports: [RoomCardComponent, CommonModule]
})
export class RoomComponent implements OnInit {
  rooms: Room[] = [];
  loading = true;

  constructor(private roomService: RoomService, @Inject(PLATFORM_ID) private platformId: Object) {}

  ngOnInit(): void {
    if (isPlatformBrowser(this.platformId)) {
      console.log('Token in RoomComponent:', localStorage.getItem('Jwt'));
    }
    this.roomService.getRooms().subscribe({
      next: (data) => {
        this.rooms = data;
        this.loading = false;
      },
      error: (err) => {
        console.error('Erro ao buscar salas', err);
        this.loading = false;
      }
    });
  }

  onReservar(salaId: string) {
    this.roomService.reservarSala(salaId).subscribe({
      next: () => alert('Reserva realizada com sucesso!'),
      error: (err) => alert('Erro ao reservar sala')
    });
  }
}