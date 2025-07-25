import {Component} from '@angular/core';
import {ToolBarComponent} from "../tool-bar/tool-bar.component";

@Component({
    selector: 'app-home',
    imports: [
        ToolBarComponent,
    ],
    templateUrl: './home.component.html',
    styleUrl: './home.component.scss'
})
export class HomeComponent {

}
