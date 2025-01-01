import { inject } from '@angular/core';
import { CanActivateFn } from '@angular/router';
import { SessionService } from '../services/session.service';

export const unAuthGuard: CanActivateFn = (route, state) => {
  const sessionService = inject(SessionService);
  return !sessionService.isLogged();
};
