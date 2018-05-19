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
import { LoginFormComponent } from './components/login-form/login-form.component';
import { FlexLayoutModule } from '@angular/flex-layout';
import { RegisterFormComponent } from './components/register-form/register-form.component';
import { UserService } from './services/user.service';
import { AuthGuardService } from './services/auth-guard.service';
import { StudentPanelComponent } from './components/student-panel/student-panel.component';
import { UserRepository } from './repository/user.repository';
import { DeclarationsComponent } from './components/declarations/declarations.component';
import { LOCALE_ID } from '@angular/core';
import { ProfileComponent } from './components/profile/profile.component';

@NgModule({
    imports: [
        BrowserModule,
        BrowserAnimationsModule,
        HttpClientModule,
        FormsModule,
        ReactiveFormsModule,
        AppRoutingModule,
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
    ],
    providers: [UserService, UserRepository, AuthGuardService,
        { provide: 'BASE_URL', useFactory: getBaseUrl },
    ],
    bootstrap: [AppComponent]
})
export class AppModule {
}




export function getBaseUrl() {
    return document.getElementsByTagName('base')[0].href;
}
