import { Component } from '@angular/core';
import {LoginformComponent} from './loginform/loginform.component';


@Component({
  selector: 'app-root',
  imports: [LoginformComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'reactive-forms';
}
