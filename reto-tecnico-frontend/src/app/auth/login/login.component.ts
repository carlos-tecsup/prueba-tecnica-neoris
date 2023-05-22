import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { first } from 'rxjs/operators';
import { UserService } from 'src/app/services/user.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  loading = false;
  submit = false;
  loginForm: FormGroup;

  constructor(
    private router: Router,
    private builder: FormBuilder,
    private userService: UserService,
  ) { 
    if(this.userService.userValue){
      this.router.navigate(['/home']);
    }

    this.loginForm = this.builder.group({
      email: ['', Validators.compose([Validators.email, Validators.required])],
      password: ['', Validators.required]
    })
  }

  ngOnInit(): void { }

  get f() { return this.loginForm.controls; }

  loginHome(values: any){
    this.submit = true;
    this.loading = true;
    this.userService.login(values.email, values.password)
      .pipe(first())
      .subscribe({
        next:() => {
          Swal.fire('¡Inicio de sesión exitoso!', 'Bienvenido de vuelta', 'success');

          this.router.navigate(['/cambio-divisa']);
        },
        error: error => {
          const errorMessage = 'Credenciales incorrectas. Verifica tus datos e intenta nuevamente.';

          // Mostrar SweetAlert2 con el mensaje de error
          Swal.fire('Error', errorMessage, 'error');
          this.loading = false;
        }
      })
  }

  goToRegister(){
    this.router.navigate(['/register'])
  }
}
