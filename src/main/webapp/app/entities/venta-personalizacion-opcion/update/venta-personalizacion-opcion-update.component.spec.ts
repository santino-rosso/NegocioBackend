import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { VentaPersonalizacionOpcionFormService } from './venta-personalizacion-opcion-form.service';
import { VentaPersonalizacionOpcionService } from '../service/venta-personalizacion-opcion.service';
import { IVentaPersonalizacionOpcion } from '../venta-personalizacion-opcion.model';
import { IOpcion } from 'app/entities/opcion/opcion.model';
import { OpcionService } from 'app/entities/opcion/service/opcion.service';
import { IPersonalizacion } from 'app/entities/personalizacion/personalizacion.model';
import { PersonalizacionService } from 'app/entities/personalizacion/service/personalizacion.service';
import { IVenta } from 'app/entities/venta/venta.model';
import { VentaService } from 'app/entities/venta/service/venta.service';

import { VentaPersonalizacionOpcionUpdateComponent } from './venta-personalizacion-opcion-update.component';

describe('VentaPersonalizacionOpcion Management Update Component', () => {
  let comp: VentaPersonalizacionOpcionUpdateComponent;
  let fixture: ComponentFixture<VentaPersonalizacionOpcionUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let ventaPersonalizacionOpcionFormService: VentaPersonalizacionOpcionFormService;
  let ventaPersonalizacionOpcionService: VentaPersonalizacionOpcionService;
  let opcionService: OpcionService;
  let personalizacionService: PersonalizacionService;
  let ventaService: VentaService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [VentaPersonalizacionOpcionUpdateComponent],
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
      .overrideTemplate(VentaPersonalizacionOpcionUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(VentaPersonalizacionOpcionUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    ventaPersonalizacionOpcionFormService = TestBed.inject(VentaPersonalizacionOpcionFormService);
    ventaPersonalizacionOpcionService = TestBed.inject(VentaPersonalizacionOpcionService);
    opcionService = TestBed.inject(OpcionService);
    personalizacionService = TestBed.inject(PersonalizacionService);
    ventaService = TestBed.inject(VentaService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call Opcion query and add missing value', () => {
      const ventaPersonalizacionOpcion: IVentaPersonalizacionOpcion = { id: 456 };
      const opcion: IOpcion = { id: 84761 };
      ventaPersonalizacionOpcion.opcion = opcion;

      const opcionCollection: IOpcion[] = [{ id: 66537 }];
      jest.spyOn(opcionService, 'query').mockReturnValue(of(new HttpResponse({ body: opcionCollection })));
      const additionalOpcions = [opcion];
      const expectedCollection: IOpcion[] = [...additionalOpcions, ...opcionCollection];
      jest.spyOn(opcionService, 'addOpcionToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ ventaPersonalizacionOpcion });
      comp.ngOnInit();

      expect(opcionService.query).toHaveBeenCalled();
      expect(opcionService.addOpcionToCollectionIfMissing).toHaveBeenCalledWith(
        opcionCollection,
        ...additionalOpcions.map(expect.objectContaining)
      );
      expect(comp.opcionsSharedCollection).toEqual(expectedCollection);
    });

    it('Should call Personalizacion query and add missing value', () => {
      const ventaPersonalizacionOpcion: IVentaPersonalizacionOpcion = { id: 456 };
      const personalizacion: IPersonalizacion = { id: 25114 };
      ventaPersonalizacionOpcion.personalizacion = personalizacion;

      const personalizacionCollection: IPersonalizacion[] = [{ id: 78506 }];
      jest.spyOn(personalizacionService, 'query').mockReturnValue(of(new HttpResponse({ body: personalizacionCollection })));
      const additionalPersonalizacions = [personalizacion];
      const expectedCollection: IPersonalizacion[] = [...additionalPersonalizacions, ...personalizacionCollection];
      jest.spyOn(personalizacionService, 'addPersonalizacionToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ ventaPersonalizacionOpcion });
      comp.ngOnInit();

      expect(personalizacionService.query).toHaveBeenCalled();
      expect(personalizacionService.addPersonalizacionToCollectionIfMissing).toHaveBeenCalledWith(
        personalizacionCollection,
        ...additionalPersonalizacions.map(expect.objectContaining)
      );
      expect(comp.personalizacionsSharedCollection).toEqual(expectedCollection);
    });

    it('Should call Venta query and add missing value', () => {
      const ventaPersonalizacionOpcion: IVentaPersonalizacionOpcion = { id: 456 };
      const venta: IVenta = { id: 29629 };
      ventaPersonalizacionOpcion.venta = venta;

      const ventaCollection: IVenta[] = [{ id: 20949 }];
      jest.spyOn(ventaService, 'query').mockReturnValue(of(new HttpResponse({ body: ventaCollection })));
      const additionalVentas = [venta];
      const expectedCollection: IVenta[] = [...additionalVentas, ...ventaCollection];
      jest.spyOn(ventaService, 'addVentaToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ ventaPersonalizacionOpcion });
      comp.ngOnInit();

      expect(ventaService.query).toHaveBeenCalled();
      expect(ventaService.addVentaToCollectionIfMissing).toHaveBeenCalledWith(
        ventaCollection,
        ...additionalVentas.map(expect.objectContaining)
      );
      expect(comp.ventasSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const ventaPersonalizacionOpcion: IVentaPersonalizacionOpcion = { id: 456 };
      const opcion: IOpcion = { id: 20512 };
      ventaPersonalizacionOpcion.opcion = opcion;
      const personalizacion: IPersonalizacion = { id: 27897 };
      ventaPersonalizacionOpcion.personalizacion = personalizacion;
      const venta: IVenta = { id: 30345 };
      ventaPersonalizacionOpcion.venta = venta;

      activatedRoute.data = of({ ventaPersonalizacionOpcion });
      comp.ngOnInit();

      expect(comp.opcionsSharedCollection).toContain(opcion);
      expect(comp.personalizacionsSharedCollection).toContain(personalizacion);
      expect(comp.ventasSharedCollection).toContain(venta);
      expect(comp.ventaPersonalizacionOpcion).toEqual(ventaPersonalizacionOpcion);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IVentaPersonalizacionOpcion>>();
      const ventaPersonalizacionOpcion = { id: 123 };
      jest.spyOn(ventaPersonalizacionOpcionFormService, 'getVentaPersonalizacionOpcion').mockReturnValue(ventaPersonalizacionOpcion);
      jest.spyOn(ventaPersonalizacionOpcionService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ ventaPersonalizacionOpcion });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: ventaPersonalizacionOpcion }));
      saveSubject.complete();

      // THEN
      expect(ventaPersonalizacionOpcionFormService.getVentaPersonalizacionOpcion).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(ventaPersonalizacionOpcionService.update).toHaveBeenCalledWith(expect.objectContaining(ventaPersonalizacionOpcion));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IVentaPersonalizacionOpcion>>();
      const ventaPersonalizacionOpcion = { id: 123 };
      jest.spyOn(ventaPersonalizacionOpcionFormService, 'getVentaPersonalizacionOpcion').mockReturnValue({ id: null });
      jest.spyOn(ventaPersonalizacionOpcionService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ ventaPersonalizacionOpcion: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: ventaPersonalizacionOpcion }));
      saveSubject.complete();

      // THEN
      expect(ventaPersonalizacionOpcionFormService.getVentaPersonalizacionOpcion).toHaveBeenCalled();
      expect(ventaPersonalizacionOpcionService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IVentaPersonalizacionOpcion>>();
      const ventaPersonalizacionOpcion = { id: 123 };
      jest.spyOn(ventaPersonalizacionOpcionService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ ventaPersonalizacionOpcion });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(ventaPersonalizacionOpcionService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });

  describe('Compare relationships', () => {
    describe('compareOpcion', () => {
      it('Should forward to opcionService', () => {
        const entity = { id: 123 };
        const entity2 = { id: 456 };
        jest.spyOn(opcionService, 'compareOpcion');
        comp.compareOpcion(entity, entity2);
        expect(opcionService.compareOpcion).toHaveBeenCalledWith(entity, entity2);
      });
    });

    describe('comparePersonalizacion', () => {
      it('Should forward to personalizacionService', () => {
        const entity = { id: 123 };
        const entity2 = { id: 456 };
        jest.spyOn(personalizacionService, 'comparePersonalizacion');
        comp.comparePersonalizacion(entity, entity2);
        expect(personalizacionService.comparePersonalizacion).toHaveBeenCalledWith(entity, entity2);
      });
    });

    describe('compareVenta', () => {
      it('Should forward to ventaService', () => {
        const entity = { id: 123 };
        const entity2 = { id: 456 };
        jest.spyOn(ventaService, 'compareVenta');
        comp.compareVenta(entity, entity2);
        expect(ventaService.compareVenta).toHaveBeenCalledWith(entity, entity2);
      });
    });
  });
});
