import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {FormControl} from "@angular/forms";
import {DeclarationService} from "../../../services/declaration.service";
import {UserService} from "../../../services/user.service";
import {UserResource} from "../../../resources/user.resource";
import {MatTableDataSource} from "@angular/material";
import {DocumentResource} from "../../../resources/document.resource";
import * as FileSaver from 'file-saver';

@Component({
    selector: 'app-users-declarations',
    templateUrl: './users-declarations.component.html',
    styleUrls: ['./users-declarations.component.css']
})
export class UsersDeclarationsComponent implements OnInit {
    rooms: Array<String> = [];
    dates: Array<Date> = [];
    selectedRooms: Array<String> = [];
    lastSelectedRooms: Array<String> = [];
    selectedDates: Array<Date> = [];
    usersWithDeclarations: Array<UserResource> = [];
    displayedColumns = ['Index', 'FirstName', "LastName", 'Time', 'Documents'];

    constructor(private declarationService: DeclarationService, private userService: UserService) {
        this.declarationService.getRoomNames().subscribe(
            (res: Array<String>) => {
                this.rooms = res;
            }
        )
        this.userService.getUsersWithDeclarations().subscribe(
            (res: UserResource[]) => {
                this.usersWithDeclarations = res;
                console.log(res);
            }
        )
    }

    ngOnInit() {
    }

    onChangeRoom() {
        console.log(this.selectedRooms);
        if (this.selectedRooms.length > this.lastSelectedRooms.length) {
            let missing = this.selectedRooms.filter(item => this.lastSelectedRooms.indexOf(item) < 0)[0];
            this.declarationService.getDatesForRoom(missing).subscribe(
                (res: Date[]) => {
                    console.log(res);
                    let response = res.filter(x => this.dates.indexOf(x) === -1);
                    this.dates = this.dates.concat(response);
                }
            );
        } else {
            let missing = this.lastSelectedRooms.filter(item => this.selectedRooms.indexOf(item) < 0)[0];
            let otherDates: Date[] = [];
            for (let room of this.selectedRooms) {
                this.declarationService.getDatesForRoom(room).subscribe(
                    (res: Date[]) => {
                        console.log(res);
                        let response = res.filter(x => otherDates.indexOf(x) === -1);
                        otherDates = otherDates.concat(response);
                    }
                );
            }
            let datesToDelete: Date[] = [];
            this.declarationService.getDatesForRoom(missing).subscribe(
                (res: Date[]) => {
                    console.log(res);
                    datesToDelete = res;
                    console.log(otherDates);
                    console.log(datesToDelete);
                    datesToDelete = datesToDelete.filter(x => otherDates.indexOf(x) === -1);
                    this.dates = this.dates.filter(x => datesToDelete.indexOf(x) === -1);
                }
            );

        }

        this.lastSelectedRooms = this.selectedRooms;
    }

    checkUsers(date, room) {
        let users = this.getUsers(date, room);
        return users.length > 0;
    }

    getUsers(date, room) {
        return this.usersWithDeclarations
            .filter((x: UserResource) => x.declarationCommand.room === room && x.declarationCommand.date === date)
    }

    getDataSource(date, room) {
        return new MatTableDataSource(this.getUsers(date, room));
    }

    downloadFile(document1: DocumentResource) {
        console.log('start download:', document1);
        var byteString;
        byteString = atob(document1.data);

        var ia = new Uint8Array(byteString.length);
        for (var i = 0; i < byteString.length; i++) {
            ia[i] = byteString.charCodeAt(i);
        }

        let blob = new Blob([ia], {type: document1.fileType});
        FileSaver.saveAs(blob, document1.fileName);
    }

    downloadPDF() {
        this.declarationService.downloadPDF(this.selectedRooms, this.selectedDates).subscribe(
            (res: DocumentResource) => this.downloadFile(res))
    }
}
