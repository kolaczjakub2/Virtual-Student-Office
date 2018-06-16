import {UserResource} from './user.resource';
import {DocumentResource} from "./document.resource";

export class DeclarationResource {

    id: number;
    User: UserResource;
    Status: string;
    date = new Date();
    time = new Date();
    room: string;
    documentCommands: Array<DocumentResource>;
}
