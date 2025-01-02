import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { SessionService } from '../services/session/session.service';

export const unAuthGuard: CanActivateFn = (route, state) => {
  const sessionService = inject(SessionService);
  const router = inject(Router);
  if(sessionService.isLogged()) {
    router.navigateByUrl('/chat');
    return false;
  }
  return true;
};
