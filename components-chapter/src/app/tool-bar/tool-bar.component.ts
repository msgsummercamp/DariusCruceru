import {Component} from '@angular/core';
import {MatToolbar, MatToolbarRow} from '@angular/material/toolbar';
import {ButtonComponent} from '../button/button.component';
import {MatIcon} from '@angular/material/icon';
import {MatButton, MatMiniFabButton} from '@angular/material/button';
import {RouterLink, RouterLinkActive} from "@angular/router";

@Component({
    selector: 'app-tool-bar',
    imports: [MatToolbar,  MatToolbarRow, MatMiniFabButton, MatIcon, RouterLink, RouterLinkActive, MatButton],
    templateUrl: './tool-bar.component.html',
    styleUrl: './tool-bar.component.scss',
})
export class ToolBarComponent {

}
