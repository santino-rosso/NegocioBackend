import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import { DispositivoFormService, DispositivoFormGroup } from './dispositivo-form.service';
import { IDispositivo } from '../dispositivo.model';
import { DispositivoService } from '../service/dispositivo.service';
import { AlertError } from 'app/shared/alert/alert-error.model';
import { EventManager, EventWithContent } from 'app/core/util/event-manager.service';
import { DataUtils, FileLoadError } from 'app/core/util/data-util.service';
import { IAdicional } from 'app/entities/adicional/adicional.model';
import { AdicionalService } from 'app/entities/adicional/service/adicional.service';

@Component({
  selector: 'jhi-dispositivo-update',
  templateUrl: './dispositivo-update.component.html',
})
export class DispositivoUpdateComponent implements OnInit {
  isSaving = false;
  dispositivo: IDispositivo | null = null;

  adicionalsSharedCollection: IAdicional[] = [];

  editForm: DispositivoFormGroup = this.dispositivoFormService.createDispositivoFormGroup();

  constructor(
    protected dataUtils: DataUtils,
    protected eventManager: EventManager,
    protected dispositivoService: DispositivoService,
    protected dispositivoFormService: DispositivoFormService,
    protected adicionalService: AdicionalService,
    protected activatedRoute: ActivatedRoute
  ) {}

  compareAdicional = (o1: IAdicional | null, o2: IAdicional | null): boolean => this.adicionalService.compareAdicional(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ dispositivo }) => {
      this.dispositivo = dispositivo;
      if (dispositivo) {
        this.updateForm(dispositivo);
      }

      this.loadRelationshipsOptions();
    });
  }

  byteSize(base64String: string): string {
    return this.dataUtils.byteSize(base64String);
  }

  openFile(base64String: string, contentType: string | null | undefined): void {
    this.dataUtils.openFile(base64String, contentType);
  }

  setFileData(event: Event, field: string, isImage: boolean): void {
    this.dataUtils.loadFileToForm(event, this.editForm, field, isImage).subscribe({
      error: (err: FileLoadError) =>
        this.eventManager.broadcast(new EventWithContent<AlertError>('negocioApp.error', { ...err, key: 'error.file.' + err.key })),
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const dispositivo = this.dispositivoFormService.getDispositivo(this.editForm);
    if (dispositivo.id !== null) {
      this.subscribeToSaveResponse(this.dispositivoService.update(dispositivo));
    } else {
      this.subscribeToSaveResponse(this.dispositivoService.create(dispositivo));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDispositivo>>): void {
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

  protected updateForm(dispositivo: IDispositivo): void {
    this.dispositivo = dispositivo;
    this.dispositivoFormService.resetForm(this.editForm, dispositivo);

    this.adicionalsSharedCollection = this.adicionalService.addAdicionalToCollectionIfMissing<IAdicional>(
      this.adicionalsSharedCollection,
      ...(dispositivo.adicionales ?? [])
    );
  }

  protected loadRelationshipsOptions(): void {
    this.adicionalService
      .query()
      .pipe(map((res: HttpResponse<IAdicional[]>) => res.body ?? []))
      .pipe(
        map((adicionals: IAdicional[]) =>
          this.adicionalService.addAdicionalToCollectionIfMissing<IAdicional>(adicionals, ...(this.dispositivo?.adicionales ?? []))
        )
      )
      .subscribe((adicionals: IAdicional[]) => (this.adicionalsSharedCollection = adicionals));
  }
}
