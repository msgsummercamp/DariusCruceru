import { Component } from '@angular/core';
import {MatToolbar, MatToolbarRow} from '@angular/material/toolbar';
import {ButtonComponent} from '../button/button.component';
import {MatIcon} from '@angular/material/icon';
import {MatMiniFabButton} from '@angular/material/button';

@Component({
  selector: 'app-tool-bar',
  imports: [MatToolbar, ButtonComponent, MatToolbarRow, MatMiniFabButton, MatIcon],
  templateUrl: './tool-bar.component.html',
  styleUrl: './tool-bar.component.scss'
})
export class ToolBarComponent {

}
