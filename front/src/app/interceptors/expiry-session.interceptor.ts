import { HttpErrorResponse, HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { catchError, of, throwError } from 'rxjs';
import { SessionService } from '../services/session/session.service';
import { Router } from '@angular/router';

const handleAuthError = (err : HttpErrorResponse) => {
  const sessionService = inject(SessionService);
  const router = inject(Router);
  if (err.status === 401 || err.status === 403 || err.status === 498) {
    sessionService.signOut();
    router.navigateByUrl(`/sign-in`);
    return of(err.message);
  }
  return throwError(() => err);
}

export const expirySessionInterceptor: HttpInterceptorFn = (req, next) => {
  const obs = next(req);
  obs.pipe(
    catchError(err => handleAuthError(err))
  );
  return obs;
};
