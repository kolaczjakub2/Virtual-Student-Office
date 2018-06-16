import {Component, OnInit} from '@angular/core';
import {UserService} from '../../../services/user.service';
import {MatSnackBar} from '@angular/material';
import {Router} from '@angular/router';
import {UserResource} from "../../../resources/user.resource";

@Component({
    selector: 'app-login-form',
    templateUrl: './login-form.component.html',
    styleUrls: ['./login-form.component.css']
})
export class LoginFormComponent implements OnInit {

    User: UserResource = new UserResource();

    constructor(private userService: UserService, public snackBar: MatSnackBar, private router: Router) {
    }

    ngOnInit() {
        if (localStorage.getItem('currentUser')) {
            this.router.navigate(['office/panel'])
        }
    }

    login() {
        this.userService.login(this.User).subscribe((response: UserResource) => {

                localStorage.setItem('userId', response.Id.toString())
                if (response.Role === "Student")
                    this.router.navigate(['student/panel'])
                else
                    this.router.navigate(['office/panel'])
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
