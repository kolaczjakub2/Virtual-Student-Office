import { NgModule, ErrorHandler } from '@angular/core';
import { RouterModule } from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClientModule } from '@angular/common/http';
import { CarouselModule } from 'ngx-bootstrap/carousel';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './components/app.component';
import { AngularMaterialModule } from './AngularMaterial.module';
import { LoginFormComponent } from './components/login_register/login-form/login-form.component';
import { FlexLayoutModule } from '@angular/flex-layout';
import { RegisterFormComponent } from './components/login_register/register-form/register-form.component';
import { UserService } from './services/user.service';
import { AuthGuardService } from './services/auth-guard.service';
import { StudentPanelComponent } from './components/student-panel/student-panel.component';
import { DeclarationsComponent } from './components/student-panel/declarations/declarations.component';
import { ProfileComponent } from './components/student-panel/profile/profile.component';
import { OfficePanelComponent } from './components/office-panel/office-panel.component';
import { TermsComponent } from './components/office-panel/terms/terms.component';
import { TermsFormComponent } from './components/office-panel/terms/terms-form/terms-form.component';
import {TermService} from "./services/term.service";
import { DeclarationFormComponent } from './components/student-panel/declarations/declaration-form/declaration-form.component';
import { DeclarationService } from './services/declaration.service';
import {MatInputModule} from "@angular/material";
import { UsersDeclarationsComponent } from './components/office-panel/users-declarations/users-declarations.component';

@NgModule({
    imports: [
        BrowserModule,
        BrowserAnimationsModule,
        HttpClientModule,
        FormsModule,
        ReactiveFormsModule,
        AppRoutingModule,
        MatInputModule,
        FlexLayoutModule,
        CarouselModule.forRoot(),
        AngularMaterialModule,
    ],
    declarations: [
        AppComponent,
        LoginFormComponent,
        RegisterFormComponent,
        StudentPanelComponent,
        DeclarationsComponent,
        ProfileComponent,
        OfficePanelComponent,
        TermsComponent,
        TermsFormComponent,
        DeclarationFormComponent,
        UsersDeclarationsComponent,
    ],
    providers: [UserService, AuthGuardService,TermService,DeclarationService,
        { provide: 'BASE_URL', useFactory: getBaseUrl },
    ],
    bootstrap: [AppComponent]
})
export class AppModule {
}




export function getBaseUrl() {
    return document.getElementsByTagName('base')[0].href;
}
