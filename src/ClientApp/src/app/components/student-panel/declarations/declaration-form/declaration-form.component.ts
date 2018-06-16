import { Component, OnInit } from '@angular/core';
import {DeclarationService} from "../../../../services/declaration.service";
import {DeclarationResource} from "../../../../resources/declaration.resource";
import {MatTableDataSource} from "@angular/material";
import {Router} from "@angular/router";

@Component({
  selector: 'app-declaration-form',
  templateUrl: './declaration-form.component.html',
  styleUrls: ['./declaration-form.component.css']
})
export class DeclarationFormComponent implements OnInit {

    rooms:String[]=[];
    dates:Date[]=[];
    declarations:DeclarationResource[];
    selectedRoom: string;
    selectedDate:Date;
    dataSource = new MatTableDataSource();
    displayedColumns = ['Id', 'Room', 'Date', 'Time', 'edit'];
  constructor(private declarationService:DeclarationService,private router:Router) {
      this.declarationService.getRoomNames().subscribe((res:String[])=>{
          this.rooms=res;
          console.log(this.rooms);
      })
  }

  ngOnInit() {
  }

    onChangeRoom(){
        this.declarationService.getDatesForRoom(this.selectedRoom).subscribe((res:Date[])=>{
            this.dates=res;
            console.log(this.dates);
        })
    }

    onChangeDate(){
        this.declarationService.getDeclarations(this.selectedRoom,this.selectedDate).subscribe((res:DeclarationResource[])=>{
            this.declarations=res;
            this.dataSource = new MatTableDataSource(this.declarations);
            console.log(this.declarations);
        })
    }

    assignUser(declarationId:number){
      this.declarationService.assignUserToDeclaration(declarationId).subscribe(
          res=>{
              this.router.navigateByUrl('/student/panel/declarations');
          }
      );
    }

}
