import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { RouterLink } from '@angular/router';
import { ToolBarComponent } from '../tool-bar/tool-bar.component';
@Component({
  selector: 'app-login-component',
  imports: [
    FormsModule,
    MatButtonModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    ToolBarComponent
  ],
  templateUrl: './login-component.component.html',
  styleUrl: './login-component.component.scss'
})
export class LoginComponent {
  username: string = '';
  password: string = '';

  login() {
    if (this.username && this.password) {
      console.log('Login attempt with:', this.username);

    }
  }
}
