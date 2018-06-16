import {Injectable} from '@angular/core';
import {TermResource} from "../resources/term.resource";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs/Observable";

@Injectable()
export class TermService {

    constructor(private http:HttpClient) {
    }

    saveTerm(term:TermResource){
        return this.http.post<TermResource>("http://localhost:8080/terms/", term).pipe();

    }

    getTerm(id: number) {
        return this.http.get("http://localhost:8080/terms/"+id ).pipe();

    }

    getTerms() {
        return this.http.get("http://localhost:8080/terms/" ).pipe();

    }

    deleteTerm(termId: number) {
        return this.http.delete("http://localhost:8080/terms/"+termId ).pipe();
    }
}
