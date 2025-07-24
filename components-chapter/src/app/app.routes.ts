import {provideRouter, Routes} from '@angular/router';
import {HomeComponent} from "./home/home.component";
import {PageNotFoundComponent} from "./page-not-found/page-not-found.component";
import {bootstrapApplication} from "@angular/platform-browser";
import {AppComponent} from "./app.component";

export const routes:Routes = [
    {path: '', redirectTo: '/home', pathMatch: 'full'},
    {path: 'home', component: HomeComponent},
    {path: 'login',
    loadComponent: () => import('./login-component/login-component.component').then(m => m.LoginComponent)},
    {path: '**', component: PageNotFoundComponent}
]

bootstrapApplication(AppComponent, {
    providers: [provideRouter(routes)],
});
