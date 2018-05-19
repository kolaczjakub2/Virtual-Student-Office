import { Component, OnInit } from '@angular/core';
import { UserService } from '../../services/user.service';
import { MatSnackBar } from '@angular/material';
import { Router } from '@angular/router';
import {UserResource} from "../../resources/user.resource";

@Component({
    selector: 'app-login-form',
    templateUrl: './login-form.component.html',
    styleUrls: ['./login-form.component.css']
})
export class LoginFormComponent implements OnInit {

    User: UserResource = new UserResource();
    constructor(private userService: UserService, public snackBar: MatSnackBar,private router:Router) { }

    ngOnInit() {
        if (localStorage.getItem('currentUser')) {
            this.router.navigate(['student/panel'])
        }
    }

    login() {
        this.userService.login(this.User).subscribe(response => {
            console.log(response);
            localStorage.setItem('currentUser', JSON.stringify({
                username: this.User.CardNumber, token: response
            }));
            this.router.navigate(['student/panel'])
        },
            error => {
                this.openSnackBar();
            });
    }
    openSnackBar() {
        this.snackBar.open("Wrong username or password", "", {
            duration: 2000,
        });
    }
}
