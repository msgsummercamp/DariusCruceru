import { inject } from '@angular/core';
import {CanActivateFn, RedirectCommand, Router} from '@angular/router';
import { AuthService } from '../service/auth.service';

export const authGuard: CanActivateFn = (route, state) => {
  const authService = inject(AuthService);
  const router = inject(Router);

  if(authService.isAuthenticated()) {
    return true;
  }
  return new RedirectCommand(router.parseUrl('login'))
};
