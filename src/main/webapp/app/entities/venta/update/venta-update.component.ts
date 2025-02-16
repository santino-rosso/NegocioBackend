import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import { VentaFormService, VentaFormGroup } from './venta-form.service';
import { IVenta } from '../venta.model';
import { VentaService } from '../service/venta.service';
import { IAdicional } from 'app/entities/adicional/adicional.model';
import { AdicionalService } from 'app/entities/adicional/service/adicional.service';
import { ICaracteristica } from 'app/entities/caracteristica/caracteristica.model';
import { CaracteristicaService } from 'app/entities/caracteristica/service/caracteristica.service';

@Component({
  selector: 'jhi-venta-update',
  templateUrl: './venta-update.component.html',
})
export class VentaUpdateComponent implements OnInit {
  isSaving = false;
  venta: IVenta | null = null;

  adicionalsSharedCollection: IAdicional[] = [];
  caracteristicasSharedCollection: ICaracteristica[] = [];

  editForm: VentaFormGroup = this.ventaFormService.createVentaFormGroup();

  constructor(
    protected ventaService: VentaService,
    protected ventaFormService: VentaFormService,
    protected adicionalService: AdicionalService,
    protected caracteristicaService: CaracteristicaService,
    protected activatedRoute: ActivatedRoute
  ) {}

  compareAdicional = (o1: IAdicional | null, o2: IAdicional | null): boolean => this.adicionalService.compareAdicional(o1, o2);

  compareCaracteristica = (o1: ICaracteristica | null, o2: ICaracteristica | null): boolean =>
    this.caracteristicaService.compareCaracteristica(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ venta }) => {
      this.venta = venta;
      if (venta) {
        this.updateForm(venta);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const venta = this.ventaFormService.getVenta(this.editForm);
    if (venta.id !== null) {
      this.subscribeToSaveResponse(this.ventaService.update(venta));
    } else {
      this.subscribeToSaveResponse(this.ventaService.create(venta));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IVenta>>): void {
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

  protected updateForm(venta: IVenta): void {
    this.venta = venta;
    this.ventaFormService.resetForm(this.editForm, venta);

    this.adicionalsSharedCollection = this.adicionalService.addAdicionalToCollectionIfMissing<IAdicional>(
      this.adicionalsSharedCollection,
      ...(venta.adicionales ?? [])
    );
    this.caracteristicasSharedCollection = this.caracteristicaService.addCaracteristicaToCollectionIfMissing<ICaracteristica>(
      this.caracteristicasSharedCollection,
      ...(venta.caracteristicas ?? [])
    );
  }

  protected loadRelationshipsOptions(): void {
    this.adicionalService
      .query()
      .pipe(map((res: HttpResponse<IAdicional[]>) => res.body ?? []))
      .pipe(
        map((adicionals: IAdicional[]) =>
          this.adicionalService.addAdicionalToCollectionIfMissing<IAdicional>(adicionals, ...(this.venta?.adicionales ?? []))
        )
      )
      .subscribe((adicionals: IAdicional[]) => (this.adicionalsSharedCollection = adicionals));

    this.caracteristicaService
      .query()
      .pipe(map((res: HttpResponse<ICaracteristica[]>) => res.body ?? []))
      .pipe(
        map((caracteristicas: ICaracteristica[]) =>
          this.caracteristicaService.addCaracteristicaToCollectionIfMissing<ICaracteristica>(
            caracteristicas,
            ...(this.venta?.caracteristicas ?? [])
          )
        )
      )
      .subscribe((caracteristicas: ICaracteristica[]) => (this.caracteristicasSharedCollection = caracteristicas));
  }
}
