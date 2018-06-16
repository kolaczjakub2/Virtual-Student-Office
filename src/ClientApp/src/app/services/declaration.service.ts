import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";

@Injectable()
export class DeclarationService {


    constructor(private http: HttpClient) {
    }


    getRoomNames() {
        return this.http.get("http://localhost:8080/declarations/rooms/names").pipe();
    }

    getDatesForRoom(room: String) {
        return this.http.get("http://localhost:8080/declarations/rooms/" + room + "/dates").pipe();
    }

    getDeclarations(room: String, date: Date) {
        return this.http.get("http://localhost:8080/declarations/rooms/" + room + "/dates/" + date.toString());
    }

    assignUserToDeclaration(declarationId: number) {
        let userId = +localStorage.getItem("userId");
        console.log(userId + " " + declarationId);
        return this.http.put('http://localhost:8080/declarations/' + declarationId + "/user/" + userId, null,
            {headers: new HttpHeaders()});
    }

    saveDocument(formModel: any, declarationId: number) {
        return this.http.put('http://localhost:8080/declarations/' + declarationId, formModel).pipe();
    }

    downloadPDF(selectedRooms: any, selectedDates: any) {
        return this.http.put("http://localhost:8080/declarations/pdf", {
            'selectedRooms': selectedRooms,
            'selectedDates': selectedDates
        }).pipe();
    }
}
