import { ApplicationConfig, provideZoneChangeDetection } from '@angular/core';
import { provideRouter } from '@angular/router';

import { routes } from './app.routes';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import { provideHttpClient, withInterceptors } from '@angular/common/http';
import { baseUriInterceptor } from './interceptors/base-uri.interceptor';
import { jwtInterceptor } from './interceptors/jwt.interceptor';
import { expirySessionInterceptor } from './interceptors/expiry-session.interceptor';

export const appConfig: ApplicationConfig = {
  providers: [
    provideZoneChangeDetection({ eventCoalescing: true }),
    provideRouter(routes),
    provideAnimationsAsync('noop'),
    provideHttpClient(
      withInterceptors([baseUriInterceptor, jwtInterceptor, expirySessionInterceptor])
    )
  ]
};
