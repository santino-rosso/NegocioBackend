import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IVentaPersonalizacionOpcion, NewVentaPersonalizacionOpcion } from '../venta-personalizacion-opcion.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IVentaPersonalizacionOpcion for edit and NewVentaPersonalizacionOpcionFormGroupInput for create.
 */
type VentaPersonalizacionOpcionFormGroupInput = IVentaPersonalizacionOpcion | PartialWithRequiredKeyOf<NewVentaPersonalizacionOpcion>;

type VentaPersonalizacionOpcionFormDefaults = Pick<NewVentaPersonalizacionOpcion, 'id'>;

type VentaPersonalizacionOpcionFormGroupContent = {
  id: FormControl<IVentaPersonalizacionOpcion['id'] | NewVentaPersonalizacionOpcion['id']>;
  opcion: FormControl<IVentaPersonalizacionOpcion['opcion']>;
  personalizacion: FormControl<IVentaPersonalizacionOpcion['personalizacion']>;
  venta: FormControl<IVentaPersonalizacionOpcion['venta']>;
};

export type VentaPersonalizacionOpcionFormGroup = FormGroup<VentaPersonalizacionOpcionFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class VentaPersonalizacionOpcionFormService {
  createVentaPersonalizacionOpcionFormGroup(
    ventaPersonalizacionOpcion: VentaPersonalizacionOpcionFormGroupInput = { id: null }
  ): VentaPersonalizacionOpcionFormGroup {
    const ventaPersonalizacionOpcionRawValue = {
      ...this.getFormDefaults(),
      ...ventaPersonalizacionOpcion,
    };
    return new FormGroup<VentaPersonalizacionOpcionFormGroupContent>({
      id: new FormControl(
        { value: ventaPersonalizacionOpcionRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      opcion: new FormControl(ventaPersonalizacionOpcionRawValue.opcion),
      personalizacion: new FormControl(ventaPersonalizacionOpcionRawValue.personalizacion),
      venta: new FormControl(ventaPersonalizacionOpcionRawValue.venta),
    });
  }

  getVentaPersonalizacionOpcion(form: VentaPersonalizacionOpcionFormGroup): IVentaPersonalizacionOpcion | NewVentaPersonalizacionOpcion {
    return form.getRawValue() as IVentaPersonalizacionOpcion | NewVentaPersonalizacionOpcion;
  }

  resetForm(form: VentaPersonalizacionOpcionFormGroup, ventaPersonalizacionOpcion: VentaPersonalizacionOpcionFormGroupInput): void {
    const ventaPersonalizacionOpcionRawValue = { ...this.getFormDefaults(), ...ventaPersonalizacionOpcion };
    form.reset(
      {
        ...ventaPersonalizacionOpcionRawValue,
        id: { value: ventaPersonalizacionOpcionRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): VentaPersonalizacionOpcionFormDefaults {
    return {
      id: null,
    };
  }
}
