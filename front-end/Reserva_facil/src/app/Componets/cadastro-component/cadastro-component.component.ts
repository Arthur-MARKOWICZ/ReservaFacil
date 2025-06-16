import { Component } from '@angular/core';
import { HttpClient, HttpClientModule } from '@angular/common/http'; 
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-cadastro-component',
  imports: [FormsModule, CommonModule, HttpClientModule],
  templateUrl: './cadastro-component.component.html',
  styleUrl: './cadastro-component.component.css'
})
export class CadastroComponentComponent {
  email: string = '';
  senha: string = '';
  nome: string ='';

  constructor(private http: HttpClient) {}

  onSubmit() {
    const payload = {
      nome: this.nome,
      email: this.email,
      senha: this.senha
    };

    this.http.post<{}>('http://localhost:8080/auth/cadastro', payload)
      .subscribe({
        next: (response) => {
          console.log('cadastro bem-sucedido', response);

        },
        error: (error) => {
          console.error('Erro no login', error);
        }
      });
  }
}

