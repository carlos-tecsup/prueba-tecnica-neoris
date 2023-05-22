import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpRequest, HttpHandler, HttpEvent } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { UserService } from '../services/user.service';

@Injectable({
  providedIn: 'root'
})
export class InterceptorService {

  constructor(private userService:UserService) { }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const user = this.userService.userValue;
    const isLogged = user && user.token;
    const apiURL = request.url.startsWith(environment.apiURL);
    if(isLogged && apiURL){
      request = request.clone({
        setHeaders: {
          Authorization: `Bearer ${user.token}`
        }
      })
    }

    return next.handle(request);
  }
}
