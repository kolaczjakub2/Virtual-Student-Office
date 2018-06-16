import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LoginFormComponent} from './components/login_register/login-form/login-form.component';
import {RegisterFormComponent} from './components/login_register/register-form/register-form.component';
import {StudentPanelComponent} from './components/student-panel/student-panel.component';
import {DeclarationsComponent} from './components/student-panel/declarations/declarations.component';
import {ProfileComponent} from './components/student-panel/profile/profile.component';
import {AuthGuardService} from './services/auth-guard.service';
import {OfficePanelComponent} from "./components/office-panel/office-panel.component";
import {TermsComponent} from "./components/office-panel/terms/terms.component";
import {TermsFormComponent} from "./components/office-panel/terms/terms-form/terms-form.component";
import {DeclarationFormComponent} from "./components/student-panel/declarations/declaration-form/declaration-form.component";
import {UsersDeclarationsComponent} from "./components/office-panel/users-declarations/users-declarations.component";

const appRoutes: Routes = [
    {
        path: 'login',
        component: LoginFormComponent,

    },
    {
        path: 'register',
        component: RegisterFormComponent
    },
    {
        path: 'student',
        children: [
            {
                path: 'panel',
                component: StudentPanelComponent,
                children: [
                    {
                        path: 'declarations',
                        component: DeclarationsComponent
                    },
                    {
                        path: 'declarations/create',
                        component: DeclarationFormComponent
                    },
                    {
                        path: 'profile',
                        component: ProfileComponent
                    }
                ]
            }
        ]
    },
    {
        path: 'office',
        children: [
            {
                path: 'panel',
                component: OfficePanelComponent,
                children: [
                    {
                        path: 'declarations',
                        component: UsersDeclarationsComponent,
                    },
                    {
                        path: 'terms',
                        component: TermsComponent,
                    },
                    {
                        path: "terms/create",
                        component: TermsFormComponent
                    },
                    {
                        path: "terms/:id/update",
                        component: TermsFormComponent
                    }


                ]
            }
        ]
    },
    {
        path: '**',
        component: LoginFormComponent
    },

];

@NgModule({
    imports: [
        RouterModule.forRoot(
            appRoutes
        )
    ],
    exports: [
        RouterModule
    ],
    providers: []
})
export class AppRoutingModule {
}
