import { Component } from '@angular/core';
import { HttpClient, HttpClientModule } from '@angular/common/http'; 
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
@Component({
  selector: 'app-login-component',
  standalone: true,
  imports: [FormsModule, CommonModule, HttpClientModule], 
  templateUrl: './login-component.component.html',
  styleUrls: ['./login-component.component.css']
})
export class Login2Component {
  email: string = '';
  senha: string = '';

  constructor(private http: HttpClient,  private router: Router) {}

  onSubmit() {
    const payload = {
      email: this.email,
      senha: this.senha
    };

    this.http.post<{ token: string }>('http://localhost:8080/auth/login', payload)
      .subscribe({
        next: (response) => {
          console.log('Login bem-sucedido', response);
          localStorage.setItem('Jwt', response.token);
        },
        error: (error) => {
          console.error('Erro no login', error);
        }
      });
  }
  Cadastro(){
    this.router.navigate(['/cadastro']);
  }
}