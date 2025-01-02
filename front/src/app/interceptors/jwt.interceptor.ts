import { HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { SessionService } from '../services/session/session.service';

export const jwtInterceptor: HttpInterceptorFn = (req, next) => {
  const sessionService = inject(SessionService);
  if(sessionService.isLogged()) {
    req = req.clone({
      setHeaders: {
        Authorization: `Bearer ${sessionService.session?.$token}`
      }
    })
  }
  return next(req);
};
