import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { UserResource } from '../resources/user.resource';

@Injectable()
export class UserService {
    public token: string;
    public url:string="http://localhost:8080/"
    currentUser: UserResource;
    constructor(private http: HttpClient) { }

    registerUser(newUser: UserResource) {
        return this.http.post<UserResource>("http://localhost:8080/users/register", newUser).pipe();
    }

    login(User: UserResource) {
        return this.http.post(this.url+'users/login', User).pipe();
    }

    getUser(Id:number) {
        return this.http.get(this.url+'users/'+Id).pipe();
    }

    getUsersWithDeclarations(){
        return this.http.get(this.url+"users/declarations").pipe();
    }
}
