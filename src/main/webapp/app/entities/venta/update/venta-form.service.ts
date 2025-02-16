import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import dayjs from 'dayjs/esm';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';
import { IVenta, NewVenta } from '../venta.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IVenta for edit and NewVentaFormGroupInput for create.
 */
type VentaFormGroupInput = IVenta | PartialWithRequiredKeyOf<NewVenta>;

/**
 * Type that converts some properties for forms.
 */
type FormValueOf<T extends IVenta | NewVenta> = Omit<T, 'fechaVenta'> & {
  fechaVenta?: string | null;
};

type VentaFormRawValue = FormValueOf<IVenta>;

type NewVentaFormRawValue = FormValueOf<NewVenta>;

type VentaFormDefaults = Pick<NewVenta, 'id' | 'fechaVenta' | 'adicionales' | 'caracteristicas'>;

type VentaFormGroupContent = {
  id: FormControl<VentaFormRawValue['id'] | NewVenta['id']>;
  idDispositivo: FormControl<VentaFormRawValue['idDispositivo']>;
  codigo: FormControl<VentaFormRawValue['codigo']>;
  nombre: FormControl<VentaFormRawValue['nombre']>;
  descripcion: FormControl<VentaFormRawValue['descripcion']>;
  precioBase: FormControl<VentaFormRawValue['precioBase']>;
  moneda: FormControl<VentaFormRawValue['moneda']>;
  fechaVenta: FormControl<VentaFormRawValue['fechaVenta']>;
  precioFinal: FormControl<VentaFormRawValue['precioFinal']>;
  adicionales: FormControl<VentaFormRawValue['adicionales']>;
  caracteristicas: FormControl<VentaFormRawValue['caracteristicas']>;
};

export type VentaFormGroup = FormGroup<VentaFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class VentaFormService {
  createVentaFormGroup(venta: VentaFormGroupInput = { id: null }): VentaFormGroup {
    const ventaRawValue = this.convertVentaToVentaRawValue({
      ...this.getFormDefaults(),
      ...venta,
    });
    return new FormGroup<VentaFormGroupContent>({
      id: new FormControl(
        { value: ventaRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      idDispositivo: new FormControl(ventaRawValue.idDispositivo, {
        validators: [Validators.required],
      }),
      codigo: new FormControl(ventaRawValue.codigo, {
        validators: [Validators.required],
      }),
      nombre: new FormControl(ventaRawValue.nombre, {
        validators: [Validators.required],
      }),
      descripcion: new FormControl(ventaRawValue.descripcion, {
        validators: [Validators.required],
      }),
      precioBase: new FormControl(ventaRawValue.precioBase, {
        validators: [Validators.required],
      }),
      moneda: new FormControl(ventaRawValue.moneda, {
        validators: [Validators.required],
      }),
      fechaVenta: new FormControl(ventaRawValue.fechaVenta, {
        validators: [Validators.required],
      }),
      precioFinal: new FormControl(ventaRawValue.precioFinal),
      adicionales: new FormControl(ventaRawValue.adicionales ?? []),
      caracteristicas: new FormControl(ventaRawValue.caracteristicas ?? []),
    });
  }

  getVenta(form: VentaFormGroup): IVenta | NewVenta {
    return this.convertVentaRawValueToVenta(form.getRawValue() as VentaFormRawValue | NewVentaFormRawValue);
  }

  resetForm(form: VentaFormGroup, venta: VentaFormGroupInput): void {
    const ventaRawValue = this.convertVentaToVentaRawValue({ ...this.getFormDefaults(), ...venta });
    form.reset(
      {
        ...ventaRawValue,
        id: { value: ventaRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): VentaFormDefaults {
    const currentTime = dayjs();

    return {
      id: null,
      fechaVenta: currentTime,
      adicionales: [],
      caracteristicas: [],
    };
  }

  private convertVentaRawValueToVenta(rawVenta: VentaFormRawValue | NewVentaFormRawValue): IVenta | NewVenta {
    return {
      ...rawVenta,
      fechaVenta: dayjs(rawVenta.fechaVenta, DATE_TIME_FORMAT),
    };
  }

  private convertVentaToVentaRawValue(
    venta: IVenta | (Partial<NewVenta> & VentaFormDefaults)
  ): VentaFormRawValue | PartialWithRequiredKeyOf<NewVentaFormRawValue> {
    return {
      ...venta,
      fechaVenta: venta.fechaVenta ? venta.fechaVenta.format(DATE_TIME_FORMAT) : undefined,
      adicionales: venta.adicionales ?? [],
      caracteristicas: venta.caracteristicas ?? [],
    };
  }
}
