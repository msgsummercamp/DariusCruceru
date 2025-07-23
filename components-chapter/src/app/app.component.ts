import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {ButtonComponent} from './button/button.component';
import {ToolBarComponent} from './tool-bar/tool-bar.component';
import {MatIcon} from '@angular/material/icon';


@Component({
  standalone:true,
  selector: 'app-root',
  imports: [RouterOutlet, ToolBarComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'components-chapter';
}
