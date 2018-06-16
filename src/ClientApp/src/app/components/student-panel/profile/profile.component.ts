import {Component, Input, OnInit} from '@angular/core';
import {UserResource} from '../../../resources/user.resource';
import {UserService} from '../../../services/user.service';

@Component({
    selector: 'app-profile',
    templateUrl: './profile.component.html',
    styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

    constructor(private userService: UserService) {
    }

    @Input() user: UserResource;

    ngOnInit() {
        if (this.user == null) {
            this.userService.getUser(+localStorage.getItem("userId")).subscribe((response: UserResource) => {
                console.log(response);
                this.user = response;
            });
        }
    }
}
