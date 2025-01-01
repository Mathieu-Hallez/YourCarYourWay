import { Routes } from '@angular/router';
import { unAuthGuard } from './guards/un-auth.guard';
import { authGuard } from './guards/auth.guard';
import { SignInComponent } from './pages/sign-in/sign-in.component';
import { ChatComponent } from './pages/chat/chat.component';

export const routes: Routes = [
    {
        path: '',
        pathMatch: 'full',
        redirectTo: 'sign-in'
    },
    {
        path: 'sign-in',
        canActivate: [unAuthGuard],
        component: SignInComponent
    },
    {
        path: 'chat',
        canActivate: [authGuard],
        component: ChatComponent
    },
    {
        path: '**',
        redirectTo: ''
    }
];
