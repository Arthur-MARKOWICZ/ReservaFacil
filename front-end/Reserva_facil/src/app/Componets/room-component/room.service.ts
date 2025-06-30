import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

export interface Room {
  id: string;
  nome: string;
  capacidade: number;
  disponivel: boolean;
}

@Injectable({
  providedIn: 'root'
})
export class RoomService {
  private apiUrl = 'http://localhost:8080/room/todos';
  private reservaUrl = 'http://localhost:8080/api/reservas';

  constructor(private http: HttpClient) {}

  getRooms(): Observable<Room[]> {
    return this.http.get<any>(this.apiUrl).pipe(
      map(response => {
        if (Array.isArray(response)) {
          return response; 
        } else if (response.content) {
          return response.content;
        } else if (response.data) {
          return response.data; 
        } else if (response.rooms) {
          return response.rooms; 
        } else {
          console.warn('Unexpected response format', response);
          return [];
        }
      })
    );
  }

  reservarSala(salaId: string): Observable<any> {
    const token = localStorage.getItem('Jwt');
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });

    return this.http.post(
      this.reservaUrl,
      { salaId },
      { headers }
    );
  }
}