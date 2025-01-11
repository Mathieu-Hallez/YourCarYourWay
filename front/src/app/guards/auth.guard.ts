import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { SessionService } from '../services/session/session.service';

export const authGuard: CanActivateFn = (_route, _state) => {
  const sessionService = inject(SessionService);
  const router = inject(Router);
  if(!sessionService.isLogged()) {
    router.navigateByUrl('/sign-in');
    return false;
  }
  return true;
};
