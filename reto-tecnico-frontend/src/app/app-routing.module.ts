import { RouterModule, Routes } from "@angular/router";
import { LoginComponent } from "./auth/login/login.component";
import { RegisterComponent } from "./auth/register/register.component";
import { AuthGuard } from "./jwt/auth.guard";
import { NgModule } from "@angular/core";
import { CambioDivisaComponent } from "./cambio-divisa/cambio-divisa.component";

const routes: Routes = [
    {
      path: '',
      component: LoginComponent
    },
    {
      path: 'register',
      component: RegisterComponent
    },
    {
      path: 'cambio-divisa',
      canActivate: [AuthGuard],
      component: CambioDivisaComponent
    }
]

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule],
    providers: [AuthGuard]
  })
  export class AppRoutingModule { }