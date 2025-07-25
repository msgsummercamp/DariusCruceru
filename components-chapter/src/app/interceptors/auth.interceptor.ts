import {HttpInterceptorFn} from '@angular/common/http';
import {inject} from '@angular/core';
import {AuthService} from '../service/auth.service';

class TokenProvider {
    private static readonly TOKEN_KEY = 'token';

    static getToken(): string {
        return this.TOKEN_KEY;
    }
}

export const authInterceptor: HttpInterceptorFn = (req, next) => {
    const authService = inject(AuthService);

    if (authService.isAuthenticated()) {
        const token = TokenProvider.getToken();

        const cloned = req.clone({
            setHeaders: {
                Authorization: `Bearer ${token}`
            }
        });
        return next(cloned);
    }

    return next(req);
};

export { TokenProvider };


