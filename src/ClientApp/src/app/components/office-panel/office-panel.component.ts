import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";

@Component({
  selector: 'app-office-panel',
  templateUrl: './office-panel.component.html',
  styleUrls: ['./office-panel.component.css']
})
export class OfficePanelComponent implements OnInit {

  constructor(private router:Router) { }

  ngOnInit() {
  }
    Logout() {
        localStorage.removeItem("currentUser");
        this.router.navigate(['/login']);
    }
}
