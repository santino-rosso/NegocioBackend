import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import { CaracteristicaFormService, CaracteristicaFormGroup } from './caracteristica-form.service';
import { ICaracteristica } from '../caracteristica.model';
import { CaracteristicaService } from '../service/caracteristica.service';
import { IDispositivo } from 'app/entities/dispositivo/dispositivo.model';
import { DispositivoService } from 'app/entities/dispositivo/service/dispositivo.service';

@Component({
  selector: 'jhi-caracteristica-update',
  templateUrl: './caracteristica-update.component.html',
})
export class CaracteristicaUpdateComponent implements OnInit {
  isSaving = false;
  caracteristica: ICaracteristica | null = null;

  dispositivosSharedCollection: IDispositivo[] = [];

  editForm: CaracteristicaFormGroup = this.caracteristicaFormService.createCaracteristicaFormGroup();

  constructor(
    protected caracteristicaService: CaracteristicaService,
    protected caracteristicaFormService: CaracteristicaFormService,
    protected dispositivoService: DispositivoService,
    protected activatedRoute: ActivatedRoute
  ) {}

  compareDispositivo = (o1: IDispositivo | null, o2: IDispositivo | null): boolean => this.dispositivoService.compareDispositivo(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ caracteristica }) => {
      this.caracteristica = caracteristica;
      if (caracteristica) {
        this.updateForm(caracteristica);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const caracteristica = this.caracteristicaFormService.getCaracteristica(this.editForm);
    if (caracteristica.id !== null) {
      this.subscribeToSaveResponse(this.caracteristicaService.update(caracteristica));
    } else {
      this.subscribeToSaveResponse(this.caracteristicaService.create(caracteristica));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICaracteristica>>): void {
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

  protected updateForm(caracteristica: ICaracteristica): void {
    this.caracteristica = caracteristica;
    this.caracteristicaFormService.resetForm(this.editForm, caracteristica);

    this.dispositivosSharedCollection = this.dispositivoService.addDispositivoToCollectionIfMissing<IDispositivo>(
      this.dispositivosSharedCollection,
      caracteristica.dispositivo
    );
  }

  protected loadRelationshipsOptions(): void {
    this.dispositivoService
      .query()
      .pipe(map((res: HttpResponse<IDispositivo[]>) => res.body ?? []))
      .pipe(
        map((dispositivos: IDispositivo[]) =>
          this.dispositivoService.addDispositivoToCollectionIfMissing<IDispositivo>(dispositivos, this.caracteristica?.dispositivo)
        )
      )
      .subscribe((dispositivos: IDispositivo[]) => (this.dispositivosSharedCollection = dispositivos));
  }
}
