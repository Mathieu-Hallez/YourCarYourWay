import { HttpInterceptorFn } from '@angular/common/http';
import { environment } from '../../environments/environment';

export const baseUriInterceptor: HttpInterceptorFn = (req, next) => {
  return next(req.clone({url: `${environment.baseURI}${req.url}`}));
};
