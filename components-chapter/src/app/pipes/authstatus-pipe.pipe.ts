import {inject, Pipe, PipeTransform} from '@angular/core';
import {AuthService} from "../service/auth.service";

@Pipe({
  name: 'authstatusPipe'
})
export class AuthstatusPipePipe implements PipeTransform {

  private authService = inject(AuthService);

  transform(value: any, mode: 'text' = 'text'): string {
    const isAuthenticated = this.authService.isAuthenticated;
    return isAuthenticated() ? 'Logged in' : 'Logged out';
  }

}
