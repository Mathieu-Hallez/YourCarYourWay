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
    // if you've caught / handled the error, you don't want to rethrow it unless you also want downstream consumers to have to handle it as well.
    return of(err.message); // or EMPTY may be appropriate here
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
