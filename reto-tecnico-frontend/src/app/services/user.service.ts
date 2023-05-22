import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable} from 'rxjs';
import { map } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { User } from '../models/user.model';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private userSubject: BehaviorSubject<User>;
  public user: Observable<User>;

  constructor(
    private router:Router,
    private http:HttpClient
  ) { 
    this.userSubject = new BehaviorSubject<User>(JSON.parse(localStorage.getItem('account')!));
    this.user = this.userSubject.asObservable();
  }

  public get userValue(): User {
    return this.userSubject.value;
  }

  register(user: User) {
    return this.http.post(`${environment.apiURL}/auth/registrar-usuario`, user);
  }

  login(email: string, password: string){
    return this.http.post<User>(`${environment.apiURL}/auth/login`, { email, password })
            .pipe(map(user => {
                localStorage.setItem('user', JSON.stringify(user));
                this.userSubject.next(user);
                return user;
    }));
  }

  logout() {
    localStorage.removeItem('account');
    this.userSubject.next(null as any);
    this.router.navigate(['']);
  }
}