import { UserResource } from '../resources/user.resource';
import { AddressResource } from '../resources/address.resource';
import { DeclarationResource } from '../resources/declaration.resource';


export class UserRepository {

    getUser(): UserResource {
        const user = new UserResource();
        user.Id = 1;
        user.Address = <AddressResource>{
            City: 'Tenczynek',
            HouseNumber: 7,
            Street: 'Na Skałki',
            ZipCode: '32-067'
        };
        user.CardNumber = 278946;
        user.Email = 'kolacz.jakub2@gmail.com';
        user.FirstName = 'Jakub';
        user.LastName = 'Kołacz';
        user.Password = 'blabla';
        user.PhoneNumber = 797793336;
        user.Role = 'Student';
        user.Declaration = new DeclarationResource();
        user.Declaration.Room = "G122";
        user.Declaration.ListOfDocuments.push("file.doc","file.jpg","file.pdf");
        return user;
    }
}
