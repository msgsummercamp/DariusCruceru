import {Component, signal} from '@angular/core';
import {DogService} from './dog.service';
import {CommonModule} from '@angular/common';
import {MatButton} from '@angular/material/button';

@Component({
  selector: 'app-dog',
  templateUrl: './dog.component.html',
  standalone:true,
  imports: [CommonModule, MatButton],
})
export class DogComponent{
  dogImage = signal<string | null>(null);

  constructor(private dogService : DogService){}

  getDogImage(){
    this.dogService.fetchDogImage().subscribe(url => this.dogImage.set(url));
  }
}
