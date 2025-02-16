import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, ActivatedRoute, Router, convertToParamMap } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { IVentaPersonalizacionOpcion } from '../venta-personalizacion-opcion.model';
import { VentaPersonalizacionOpcionService } from '../service/venta-personalizacion-opcion.service';

import { VentaPersonalizacionOpcionRoutingResolveService } from './venta-personalizacion-opcion-routing-resolve.service';

describe('VentaPersonalizacionOpcion routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let routingResolveService: VentaPersonalizacionOpcionRoutingResolveService;
  let service: VentaPersonalizacionOpcionService;
  let resultVentaPersonalizacionOpcion: IVentaPersonalizacionOpcion | null | undefined;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: {
            snapshot: {
              paramMap: convertToParamMap({}),
            },
          },
        },
      ],
    });
    mockRouter = TestBed.inject(Router);
    jest.spyOn(mockRouter, 'navigate').mockImplementation(() => Promise.resolve(true));
    mockActivatedRouteSnapshot = TestBed.inject(ActivatedRoute).snapshot;
    routingResolveService = TestBed.inject(VentaPersonalizacionOpcionRoutingResolveService);
    service = TestBed.inject(VentaPersonalizacionOpcionService);
    resultVentaPersonalizacionOpcion = undefined;
  });

  describe('resolve', () => {
    it('should return IVentaPersonalizacionOpcion returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultVentaPersonalizacionOpcion = result;
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultVentaPersonalizacionOpcion).toEqual({ id: 123 });
    });

    it('should return null if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultVentaPersonalizacionOpcion = result;
      });

      // THEN
      expect(service.find).not.toBeCalled();
      expect(resultVentaPersonalizacionOpcion).toEqual(null);
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse<IVentaPersonalizacionOpcion>({ body: null })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultVentaPersonalizacionOpcion = result;
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultVentaPersonalizacionOpcion).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
