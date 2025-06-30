import { Component } from '@angular/core';
import { RoomComponent as SharedRoomComponent } from "../../Componets/room-component/room-component.component";

@Component({
  selector: 'app-room',
  imports: [SharedRoomComponent],
  templateUrl: './room.component.html',
  styleUrl: './room.component.css'
})
export class RoomComponent {

}
