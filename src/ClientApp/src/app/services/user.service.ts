import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { UserRepository } from '../repository/user.repository';
import { UserResource } from '../resources/user.resource';

@Injectable()
export class UserService {
    public token: string;
    currentUser: UserResource;
    constructor(private http: HttpClient, private userRepository: UserRepository) { }

    registerUser(newUser: UserResource) {
        return this.http.put<UserResource>('api/User/Register', newUser).pipe();
    }

    login(User: UserResource) {
        return this.http.post('api/User/Login', User).pipe();
    }

    getCurrentUser() {
        return this.userRepository.getUser();
    }
}
