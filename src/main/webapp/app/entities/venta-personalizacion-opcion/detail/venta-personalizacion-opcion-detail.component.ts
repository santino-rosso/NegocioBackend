import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IVentaPersonalizacionOpcion } from '../venta-personalizacion-opcion.model';

@Component({
  selector: 'jhi-venta-personalizacion-opcion-detail',
  templateUrl: './venta-personalizacion-opcion-detail.component.html',
})
export class VentaPersonalizacionOpcionDetailComponent implements OnInit {
  ventaPersonalizacionOpcion: IVentaPersonalizacionOpcion | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ ventaPersonalizacionOpcion }) => {
      this.ventaPersonalizacionOpcion = ventaPersonalizacionOpcion;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
