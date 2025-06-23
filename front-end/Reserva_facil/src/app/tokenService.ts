import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class TokenService {
getToken(): string | null {
  if (typeof window !== 'undefined') {
    const token = localStorage.getItem('Jwt');
    return token;
  }
  return null;
}
}
