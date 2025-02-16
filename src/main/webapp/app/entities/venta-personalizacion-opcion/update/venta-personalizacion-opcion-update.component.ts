import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import { VentaPersonalizacionOpcionFormService, VentaPersonalizacionOpcionFormGroup } from './venta-personalizacion-opcion-form.service';
import { IVentaPersonalizacionOpcion } from '../venta-personalizacion-opcion.model';
import { VentaPersonalizacionOpcionService } from '../service/venta-personalizacion-opcion.service';
import { IOpcion } from 'app/entities/opcion/opcion.model';
import { OpcionService } from 'app/entities/opcion/service/opcion.service';
import { IPersonalizacion } from 'app/entities/personalizacion/personalizacion.model';
import { PersonalizacionService } from 'app/entities/personalizacion/service/personalizacion.service';
import { IVenta } from 'app/entities/venta/venta.model';
import { VentaService } from 'app/entities/venta/service/venta.service';

@Component({
  selector: 'jhi-venta-personalizacion-opcion-update',
  templateUrl: './venta-personalizacion-opcion-update.component.html',
})
export class VentaPersonalizacionOpcionUpdateComponent implements OnInit {
  isSaving = false;
  ventaPersonalizacionOpcion: IVentaPersonalizacionOpcion | null = null;

  opcionsSharedCollection: IOpcion[] = [];
  personalizacionsSharedCollection: IPersonalizacion[] = [];
  ventasSharedCollection: IVenta[] = [];

  editForm: VentaPersonalizacionOpcionFormGroup = this.ventaPersonalizacionOpcionFormService.createVentaPersonalizacionOpcionFormGroup();

  constructor(
    protected ventaPersonalizacionOpcionService: VentaPersonalizacionOpcionService,
    protected ventaPersonalizacionOpcionFormService: VentaPersonalizacionOpcionFormService,
    protected opcionService: OpcionService,
    protected personalizacionService: PersonalizacionService,
    protected ventaService: VentaService,
    protected activatedRoute: ActivatedRoute
  ) {}

  compareOpcion = (o1: IOpcion | null, o2: IOpcion | null): boolean => this.opcionService.compareOpcion(o1, o2);

  comparePersonalizacion = (o1: IPersonalizacion | null, o2: IPersonalizacion | null): boolean =>
    this.personalizacionService.comparePersonalizacion(o1, o2);

  compareVenta = (o1: IVenta | null, o2: IVenta | null): boolean => this.ventaService.compareVenta(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ ventaPersonalizacionOpcion }) => {
      this.ventaPersonalizacionOpcion = ventaPersonalizacionOpcion;
      if (ventaPersonalizacionOpcion) {
        this.updateForm(ventaPersonalizacionOpcion);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const ventaPersonalizacionOpcion = this.ventaPersonalizacionOpcionFormService.getVentaPersonalizacionOpcion(this.editForm);
    if (ventaPersonalizacionOpcion.id !== null) {
      this.subscribeToSaveResponse(this.ventaPersonalizacionOpcionService.update(ventaPersonalizacionOpcion));
    } else {
      this.subscribeToSaveResponse(this.ventaPersonalizacionOpcionService.create(ventaPersonalizacionOpcion));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IVentaPersonalizacionOpcion>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe({
      next: () => this.onSaveSuccess(),
      error: () => this.onSaveError(),
    });
  }

  protected onSaveSuccess(): void {
    this.previousState();
  }

  protected onSaveError(): void {
    // Api for inheritance.
  }

  protected onSaveFinalize(): void {
    this.isSaving = false;
  }

  protected updateForm(ventaPersonalizacionOpcion: IVentaPersonalizacionOpcion): void {
    this.ventaPersonalizacionOpcion = ventaPersonalizacionOpcion;
    this.ventaPersonalizacionOpcionFormService.resetForm(this.editForm, ventaPersonalizacionOpcion);

    this.opcionsSharedCollection = this.opcionService.addOpcionToCollectionIfMissing<IOpcion>(
      this.opcionsSharedCollection,
      ventaPersonalizacionOpcion.opcion
    );
    this.personalizacionsSharedCollection = this.personalizacionService.addPersonalizacionToCollectionIfMissing<IPersonalizacion>(
      this.personalizacionsSharedCollection,
      ventaPersonalizacionOpcion.personalizacion
    );
    this.ventasSharedCollection = this.ventaService.addVentaToCollectionIfMissing<IVenta>(
      this.ventasSharedCollection,
      ventaPersonalizacionOpcion.venta
    );
  }

  protected loadRelationshipsOptions(): void {
    this.opcionService
      .query()
      .pipe(map((res: HttpResponse<IOpcion[]>) => res.body ?? []))
      .pipe(
        map((opcions: IOpcion[]) =>
          this.opcionService.addOpcionToCollectionIfMissing<IOpcion>(opcions, this.ventaPersonalizacionOpcion?.opcion)
        )
      )
      .subscribe((opcions: IOpcion[]) => (this.opcionsSharedCollection = opcions));

    this.personalizacionService
      .query()
      .pipe(map((res: HttpResponse<IPersonalizacion[]>) => res.body ?? []))
      .pipe(
        map((personalizacions: IPersonalizacion[]) =>
          this.personalizacionService.addPersonalizacionToCollectionIfMissing<IPersonalizacion>(
            personalizacions,
            this.ventaPersonalizacionOpcion?.personalizacion
          )
        )
      )
      .subscribe((personalizacions: IPersonalizacion[]) => (this.personalizacionsSharedCollection = personalizacions));

    this.ventaService
      .query()
      .pipe(map((res: HttpResponse<IVenta[]>) => res.body ?? []))
      .pipe(
        map((ventas: IVenta[]) => this.ventaService.addVentaToCollectionIfMissing<IVenta>(ventas, this.ventaPersonalizacionOpcion?.venta))
      )
      .subscribe((ventas: IVenta[]) => (this.ventasSharedCollection = ventas));
  }
}
