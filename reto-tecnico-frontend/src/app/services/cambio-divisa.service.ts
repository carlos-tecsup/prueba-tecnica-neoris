import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CambioDivisa } from '../models/cambio-divisa.model';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CambioDivisaService {

  constructor(private http: HttpClient) { 
  }
  
    obtenerTipoCambio(cambio:CambioDivisa): Observable<any> {
      return this.http.post(`${environment.apiURL}/tipo-cambio`, cambio);
    }
  
    public obtenerMonedasDestino(): Observable<any> {
      return this.http.get(`${environment.apiURL}/tipo-cambio/monedas-destino`);
    }

    public obtenerMonedasOrigen(): Observable<any> {
      return this.http.get(`${environment.apiURL}/tipo-cambio/monedas-origen`);
    }
}
