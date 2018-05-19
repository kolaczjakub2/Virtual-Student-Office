import { Component, OnInit } from '@angular/core';
import { UserResource } from '../../resources/user.resource';
import { UserService } from '../../services/user.service';

@Component({
    selector: 'app-profile',
    templateUrl: './profile.component.html',
    styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

    constructor(private userService: UserService) { }

    user: UserResource;
    ngOnInit() {
        this.user = this.userService.getCurrentUser();
    }

}
