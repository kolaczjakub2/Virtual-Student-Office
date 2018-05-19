import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-student-panel',
  templateUrl: './student-panel.component.html',
  styleUrls: ['./student-panel.component.css']
})
export class StudentPanelComponent implements OnInit {

  constructor(private router:Router) { }

  ngOnInit() {
  }
    Logout() {
        localStorage.removeItem("currentUser");
        this.router.navigate(['/login']);
}
}
