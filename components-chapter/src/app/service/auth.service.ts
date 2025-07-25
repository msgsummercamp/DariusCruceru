import {Injectable, signal} from '@angular/core';

@Injectable({
    providedIn: 'root'
})
export class AuthService {
    private readonly _isAuthenticated = signal(false);
    public readonly isAuthenticated = this._isAuthenticated.asReadonly();

    login() {
        this._isAuthenticated.set(true);
    }

    logout() {
        this._isAuthenticated.set(false);
    }
}
