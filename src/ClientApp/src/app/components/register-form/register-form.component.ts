import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { UserService } from '../../services/user.service';
import { Router } from '@angular/router';
import { UserResource } from '../../resources/user.resource';

@Component({
  selector: 'app-register-form',
  templateUrl: './register-form.component.html',
  styleUrls: ['./register-form.component.css']
})
export class RegisterFormComponent implements OnInit {

  isLinear = false;
  RoleFormGroup: FormGroup;
  AddressFormGroup: FormGroup;
  DataFormGroup: FormGroup;
  PasswordFormGroup: FormGroup;

  NewUser: UserResource = new UserResource();
  constructor(private _formBuilder: FormBuilder, private userService: UserService, private router: Router) { }

  ngOnInit() {
    this.RoleFormGroup = this._formBuilder.group({
      firstCtrl: ['', Validators.required]
    });
    this.AddressFormGroup = this._formBuilder.group({
      streetCtrl: ['', [Validators.required, Validators.pattern('^[A-z]+$')]],
      houseNumberCtrl: ['', [Validators.required, Validators.pattern('^[0-9]+$')]],
      CityCtrl: ['', [Validators.required, Validators.pattern('^[A-z]+$')]],
      ZipCodeCtrl: ['', [Validators.required, Validators.pattern("^[0-9]{2}-[0-9]{3}$")]],
      EmailCtrl: ['', [Validators.required, Validators.email]],
      PhoneNumberCtrl: ['', [Validators.minLength(9), Validators.maxLength(9), Validators.pattern('^[0-9]+$')]]
    });
    this.DataFormGroup = this._formBuilder.group({
        FirstNameCtrl: ['', [Validators.required, Validators.pattern('^[A-z]+$')]],
        LastNameCtrl: ['', [Validators.required, Validators.pattern('^[A-z]+$')]],
      CardNumberCtrl: ['', [Validators.required, Validators.pattern('^[0-9]+$')]],
    });
    this.PasswordFormGroup = this._formBuilder.group({
        PasswordCtrl: ['', Validators.required],
        ConfirmPasswordCtrl: ['', Validators.required],
    });
  }

  register() {
    this.userService.registerUser(this.NewUser).subscribe(res => this.router.navigate(['login']));
  }

}
