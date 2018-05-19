import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginFormComponent } from './components/login-form/login-form.component';
import { RegisterFormComponent } from './components/register-form/register-form.component';
import { StudentPanelComponent } from './components/student-panel/student-panel.component';
import { DeclarationsComponent } from './components/declarations/declarations.component';
import { ProfileComponent } from './components/profile/profile.component';
import { AuthGuardService } from './services/auth-guard.service';

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
                        path: 'profile',
                        component:ProfileComponent
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
    providers: [
    ]
})
export class AppRoutingModule { }
