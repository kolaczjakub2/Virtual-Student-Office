import { AddressResource } from './address.resource';
import { DeclarationResource } from './declaration.resource';

export class UserResource {
    Id: number;
    Role = '';
    FirstName: string;
    LastName: string;
    CardNumber: number;
    Email: string;
    PhoneNumber: number;
    Password: string;
    Address: AddressResource = new AddressResource();
    Declaration: DeclarationResource;
}
