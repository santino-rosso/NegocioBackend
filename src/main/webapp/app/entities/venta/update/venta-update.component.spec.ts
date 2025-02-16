import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { VentaFormService } from './venta-form.service';
import { VentaService } from '../service/venta.service';
import { IVenta } from '../venta.model';
import { IAdicional } from 'app/entities/adicional/adicional.model';
import { AdicionalService } from 'app/entities/adicional/service/adicional.service';
import { ICaracteristica } from 'app/entities/caracteristica/caracteristica.model';
import { CaracteristicaService } from 'app/entities/caracteristica/service/caracteristica.service';

import { VentaUpdateComponent } from './venta-update.component';

describe('Venta Management Update Component', () => {
  let comp: VentaUpdateComponent;
  let fixture: ComponentFixture<VentaUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let ventaFormService: VentaFormService;
  let ventaService: VentaService;
  let adicionalService: AdicionalService;
  let caracteristicaService: CaracteristicaService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [VentaUpdateComponent],
      providers: [
        FormBuilder,
        {
          provide: ActivatedRoute,
          useValue: {
            params: from([{}]),
          },
        },
      ],
    })
      .overrideTemplate(VentaUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(VentaUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    ventaFormService = TestBed.inject(VentaFormService);
    ventaService = TestBed.inject(VentaService);
    adicionalService = TestBed.inject(AdicionalService);
    caracteristicaService = TestBed.inject(CaracteristicaService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call Adicional query and add missing value', () => {
      const venta: IVenta = { id: 456 };
      const adicionales: IAdicional[] = [{ id: 37675 }];
      venta.adicionales = adicionales;

      const adicionalCollection: IAdicional[] = [{ id: 23661 }];
      jest.spyOn(adicionalService, 'query').mockReturnValue(of(new HttpResponse({ body: adicionalCollection })));
      const additionalAdicionals = [...adicionales];
      const expectedCollection: IAdicional[] = [...additionalAdicionals, ...adicionalCollection];
      jest.spyOn(adicionalService, 'addAdicionalToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ venta });
      comp.ngOnInit();

      expect(adicionalService.query).toHaveBeenCalled();
      expect(adicionalService.addAdicionalToCollectionIfMissing).toHaveBeenCalledWith(
        adicionalCollection,
        ...additionalAdicionals.map(expect.objectContaining)
      );
      expect(comp.adicionalsSharedCollection).toEqual(expectedCollection);
    });

    it('Should call Caracteristica query and add missing value', () => {
      const venta: IVenta = { id: 456 };
      const caracteristicas: ICaracteristica[] = [{ id: 88415 }];
      venta.caracteristicas = caracteristicas;

      const caracteristicaCollection: ICaracteristica[] = [{ id: 50576 }];
      jest.spyOn(caracteristicaService, 'query').mockReturnValue(of(new HttpResponse({ body: caracteristicaCollection })));
      const additionalCaracteristicas = [...caracteristicas];
      const expectedCollection: ICaracteristica[] = [...additionalCaracteristicas, ...caracteristicaCollection];
      jest.spyOn(caracteristicaService, 'addCaracteristicaToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ venta });
      comp.ngOnInit();

      expect(caracteristicaService.query).toHaveBeenCalled();
      expect(caracteristicaService.addCaracteristicaToCollectionIfMissing).toHaveBeenCalledWith(
        caracteristicaCollection,
        ...additionalCaracteristicas.map(expect.objectContaining)
      );
      expect(comp.caracteristicasSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const venta: IVenta = { id: 456 };
      const adicionales: IAdicional = { id: 46338 };
      venta.adicionales = [adicionales];
      const caracteristicas: ICaracteristica = { id: 871 };
      venta.caracteristicas = [caracteristicas];

      activatedRoute.data = of({ venta });
      comp.ngOnInit();

      expect(comp.adicionalsSharedCollection).toContain(adicionales);
      expect(comp.caracteristicasSharedCollection).toContain(caracteristicas);
      expect(comp.venta).toEqual(venta);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IVenta>>();
      const venta = { id: 123 };
      jest.spyOn(ventaFormService, 'getVenta').mockReturnValue(venta);
      jest.spyOn(ventaService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ venta });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: venta }));
      saveSubject.complete();

      // THEN
      expect(ventaFormService.getVenta).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(ventaService.update).toHaveBeenCalledWith(expect.objectContaining(venta));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IVenta>>();
      const venta = { id: 123 };
      jest.spyOn(ventaFormService, 'getVenta').mockReturnValue({ id: null });
      jest.spyOn(ventaService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ venta: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: venta }));
      saveSubject.complete();

      // THEN
      expect(ventaFormService.getVenta).toHaveBeenCalled();
      expect(ventaService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IVenta>>();
      const venta = { id: 123 };
      jest.spyOn(ventaService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ venta });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(ventaService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });

  describe('Compare relationships', () => {
    describe('compareAdicional', () => {
      it('Should forward to adicionalService', () => {
        const entity = { id: 123 };
        const entity2 = { id: 456 };
        jest.spyOn(adicionalService, 'compareAdicional');
        comp.compareAdicional(entity, entity2);
        expect(adicionalService.compareAdicional).toHaveBeenCalledWith(entity, entity2);
      });
    });

    describe('compareCaracteristica', () => {
      it('Should forward to caracteristicaService', () => {
        const entity = { id: 123 };
        const entity2 = { id: 456 };
        jest.spyOn(caracteristicaService, 'compareCaracteristica');
        comp.compareCaracteristica(entity, entity2);
        expect(caracteristicaService.compareCaracteristica).toHaveBeenCalledWith(entity, entity2);
      });
    });
  });
});
