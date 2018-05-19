import { Injectable } from '@angular/core';
import { Router } from "@angular/router";

@Injectable()
export class AuthGuardService {

    constructor(private router: Router) { }

    canActivate() {
        if (localStorage.getItem('currentUser')) {
            console.log('Access granted');
            return true;
        }
        this.router.navigate(['/login']);
        return false;
    }
}
