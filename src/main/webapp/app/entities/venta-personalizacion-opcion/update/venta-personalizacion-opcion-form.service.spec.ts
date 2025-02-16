import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../venta-personalizacion-opcion.test-samples';

import { VentaPersonalizacionOpcionFormService } from './venta-personalizacion-opcion-form.service';

describe('VentaPersonalizacionOpcion Form Service', () => {
  let service: VentaPersonalizacionOpcionFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(VentaPersonalizacionOpcionFormService);
  });

  describe('Service methods', () => {
    describe('createVentaPersonalizacionOpcionFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createVentaPersonalizacionOpcionFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            opcion: expect.any(Object),
            personalizacion: expect.any(Object),
            venta: expect.any(Object),
          })
        );
      });

      it('passing IVentaPersonalizacionOpcion should create a new form with FormGroup', () => {
        const formGroup = service.createVentaPersonalizacionOpcionFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            opcion: expect.any(Object),
            personalizacion: expect.any(Object),
            venta: expect.any(Object),
          })
        );
      });
    });

    describe('getVentaPersonalizacionOpcion', () => {
      it('should return NewVentaPersonalizacionOpcion for default VentaPersonalizacionOpcion initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createVentaPersonalizacionOpcionFormGroup(sampleWithNewData);

        const ventaPersonalizacionOpcion = service.getVentaPersonalizacionOpcion(formGroup) as any;

        expect(ventaPersonalizacionOpcion).toMatchObject(sampleWithNewData);
      });

      it('should return NewVentaPersonalizacionOpcion for empty VentaPersonalizacionOpcion initial value', () => {
        const formGroup = service.createVentaPersonalizacionOpcionFormGroup();

        const ventaPersonalizacionOpcion = service.getVentaPersonalizacionOpcion(formGroup) as any;

        expect(ventaPersonalizacionOpcion).toMatchObject({});
      });

      it('should return IVentaPersonalizacionOpcion', () => {
        const formGroup = service.createVentaPersonalizacionOpcionFormGroup(sampleWithRequiredData);

        const ventaPersonalizacionOpcion = service.getVentaPersonalizacionOpcion(formGroup) as any;

        expect(ventaPersonalizacionOpcion).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IVentaPersonalizacionOpcion should not enable id FormControl', () => {
        const formGroup = service.createVentaPersonalizacionOpcionFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewVentaPersonalizacionOpcion should disable id FormControl', () => {
        const formGroup = service.createVentaPersonalizacionOpcionFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
