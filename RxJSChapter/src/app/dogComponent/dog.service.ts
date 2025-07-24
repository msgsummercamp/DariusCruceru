import {inject, Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {map, first , Observable, pipe} from 'rxjs';

type DogApiResponse = {
  message: string;
  status: string;
}

@Injectable({
  providedIn: 'root'
})
export class DogService {
  private http = inject(HttpClient);
  private lastImage: string | null = null;

  fetchDogImage(): Observable<string> {
    return this.http.get<DogApiResponse>('https://dog.ceo/api/breeds/image/random')
      .pipe(
        first(),
        map(response => {
          this.lastImage = response.message;
          return response.message;
        }),
      );
  }

  get lastDogImage(): string | null {
    return this.lastImage;
  }
}
