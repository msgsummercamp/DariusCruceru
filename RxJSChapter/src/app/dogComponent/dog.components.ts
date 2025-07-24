import {Component, inject, signal} from '@angular/core';
import {DogService} from './dog.service';
import {CommonModule} from '@angular/common';
import {MatButton} from '@angular/material/button';

@Component({
  selector: 'app-dog',
  templateUrl: './dog.component.html',
  imports: [CommonModule, MatButton],
})
export class DogComponent{
  dogImage = signal<string | null>(null);
  private dogService = inject(DogService)


  getDogImage(){
    this.dogService.fetchDogImage().subscribe(url => this.dogImage.set(url));
  }

  get DogImageValue() {
    return this.dogImage();
  }
}
