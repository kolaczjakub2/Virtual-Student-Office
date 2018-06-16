import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {UserResource} from "../../../resources/user.resource";
import {UserService} from "../../../services/user.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {DeclarationService} from "../../../services/declaration.service";
import {DocumentResource} from "../../../resources/document.resource";
import * as FileSaver from 'file-saver';

@Component({
    selector: 'app-declarations',
    templateUrl: './declarations.component.html',
    styleUrls: ['./declarations.component.css']
})
export class DeclarationsComponent implements OnInit {

    panelOpenState: boolean = false;
    form: FormGroup;
    user: UserResource = new UserResource();
    expanded = false;
    file;
    @ViewChild('fileInput') fileInput: ElementRef;

    constructor(private userService: UserService, private fb: FormBuilder, private declarationService: DeclarationService) {
        this.getUser();
        this.form = this.fb.group({
            name: [''],
            file: null
        });
    }

    getUser() {
        this.userService.getUser(+localStorage.getItem("userId")).subscribe((response: UserResource) => {
            console.log(response);
            this.user = response;
        });
    }

    ngOnInit() {

    }

    expand() {
        this.expanded = true;
    }

    onFileChange(event) {
        let reader = new FileReader();
        if (event.target.files && event.target.files.length > 0) {
            let file = event.target.files[0];
            reader.readAsDataURL(file);

            reader.onload = () => {
                this.form.get('file').setValue({
                    filename: file.name,
                    filetype: file.type,
                    value: reader.result.split(',')[1]

                })
                console.log(reader.result);
            };
        }
    }

    onSubmit() {
        if (this.expanded == true) {
            const formModel = this.form.value;
            let object = {
                data: formModel.file.value,
                name: formModel.name,
                filename: formModel.file.filename,
                filetype: formModel.file.filetype
            }
            console.log(object);
            this.expanded = false;
            this.declarationService.saveDocument(object, this.user.declarationCommand.id).subscribe(
                res => {
                    this.getUser();
                }
            );
        } else {
            this.expand();
        }
    }

    clearFile() {
        this.form.get('avatar').setValue(null);
        this.fileInput.nativeElement.value = '';
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


}
