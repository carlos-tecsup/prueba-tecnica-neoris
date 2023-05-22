import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { CambioDivisaService } from '../services/cambio-divisa.service';
import { CambioDivisa } from '../models/cambio-divisa.model';
import { Moneda } from '../models/moneda.model';

@Component({
  selector: 'app-cambio-divisa',
  templateUrl: './cambio-divisa.component.html',
  styleUrls: ['./cambio-divisa.component.css']
})
export class CambioDivisaComponent implements OnInit {
  tipoCambioForm: FormGroup = new FormGroup({});
  errorMessage: string = '';
  cambioDivisa: CambioDivisa = new CambioDivisa();
  monto?: number;
  montoTipoCambio?: number;
  monedaOrigen?: string;
  monedaDestino?: string;
  tipoCambio?: number;
  hasData?: boolean;
  dataMonedaOrigen: Moneda[] = [];
  dataMonedaDestino: Moneda[] = [];

  constructor(private cambioDivisaService:CambioDivisaService) {
  }

  ngOnInit(): void {
    this.tipoCambioForm = new FormGroup({
      monto: new FormControl('', Validators.required),
      monedaOrigen: new FormControl('', Validators.required),
      monedaDestino: new FormControl('', Validators.required)
    });

    this.cambioDivisaService.obtenerMonedasOrigen().subscribe({
      next: (resp) => {
        this.dataMonedaOrigen =resp;
      },
      error: () => {
      }
    })

    this.cambioDivisaService.obtenerMonedasDestino().subscribe({
      next: (resp) => {
        this.dataMonedaDestino = resp;
      },
      error: () => {
      }
    })
  }

  calculateTipoCambio() {
    if (this.tipoCambioForm.valid) {
      this.errorMessage = '';

      this.cambioDivisa.monto = this.tipoCambioForm.get('monto')?.value;
      this.cambioDivisa.monedaOrigen = this.tipoCambioForm.get('monedaOrigen')?.value;
      this.cambioDivisa.monedaDestino = this.tipoCambioForm.get('monedaDestino')?.value;

      this.cambioDivisaService.obtenerTipoCambio(this.cambioDivisa).subscribe({
        next: (resp) => {
          this.hasData = Object.keys(resp).length > 0;
          this.montoTipoCambio = resp.montoTipoCambio;
          this.monto= resp.monto;
          this.monedaOrigen = resp.monedaOrigen;
          this.monedaDestino = resp.monedaDestino;
          this.tipoCambio = resp.tipoCambio;
        },
        error: () => {
          // this.toastrService.error('Error al registrar', 'Error');
        }
      })



  } else{
    this.errorMessage = 'PORFAVOR COMPLETE LOS CAMPOS REQUERIDOS';

  }
}
}
