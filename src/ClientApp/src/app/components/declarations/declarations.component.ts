import { Component, OnInit } from '@angular/core';
import { UserService } from '../../services/user.service';
import { UserResource } from '../../resources/user.resource';

@Component({
  selector: 'app-declarations',
  templateUrl: './declarations.component.html',
  styleUrls: ['./declarations.component.css']
})
export class DeclarationsComponent implements OnInit {

  user: UserResource;
  constructor(private userService: UserService) { }

  ngOnInit() {
    this.user = this.userService.getCurrentUser();
  }

}
