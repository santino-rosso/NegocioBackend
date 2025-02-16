import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IVentaPersonalizacionOpcion } from '../venta-personalizacion-opcion.model';
import {
  sampleWithRequiredData,
  sampleWithNewData,
  sampleWithPartialData,
  sampleWithFullData,
} from '../venta-personalizacion-opcion.test-samples';

import { VentaPersonalizacionOpcionService } from './venta-personalizacion-opcion.service';

const requireRestSample: IVentaPersonalizacionOpcion = {
  ...sampleWithRequiredData,
};

describe('VentaPersonalizacionOpcion Service', () => {
  let service: VentaPersonalizacionOpcionService;
  let httpMock: HttpTestingController;
  let expectedResult: IVentaPersonalizacionOpcion | IVentaPersonalizacionOpcion[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(VentaPersonalizacionOpcionService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  describe('Service methods', () => {
    it('should find an element', () => {
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.find(123).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should create a VentaPersonalizacionOpcion', () => {
      // eslint-disable-next-line @typescript-eslint/no-unused-vars
      const ventaPersonalizacionOpcion = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(ventaPersonalizacionOpcion).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a VentaPersonalizacionOpcion', () => {
      const ventaPersonalizacionOpcion = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(ventaPersonalizacionOpcion).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a VentaPersonalizacionOpcion', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of VentaPersonalizacionOpcion', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a VentaPersonalizacionOpcion', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addVentaPersonalizacionOpcionToCollectionIfMissing', () => {
      it('should add a VentaPersonalizacionOpcion to an empty array', () => {
        const ventaPersonalizacionOpcion: IVentaPersonalizacionOpcion = sampleWithRequiredData;
        expectedResult = service.addVentaPersonalizacionOpcionToCollectionIfMissing([], ventaPersonalizacionOpcion);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(ventaPersonalizacionOpcion);
      });

      it('should not add a VentaPersonalizacionOpcion to an array that contains it', () => {
        const ventaPersonalizacionOpcion: IVentaPersonalizacionOpcion = sampleWithRequiredData;
        const ventaPersonalizacionOpcionCollection: IVentaPersonalizacionOpcion[] = [
          {
            ...ventaPersonalizacionOpcion,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addVentaPersonalizacionOpcionToCollectionIfMissing(
          ventaPersonalizacionOpcionCollection,
          ventaPersonalizacionOpcion
        );
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a VentaPersonalizacionOpcion to an array that doesn't contain it", () => {
        const ventaPersonalizacionOpcion: IVentaPersonalizacionOpcion = sampleWithRequiredData;
        const ventaPersonalizacionOpcionCollection: IVentaPersonalizacionOpcion[] = [sampleWithPartialData];
        expectedResult = service.addVentaPersonalizacionOpcionToCollectionIfMissing(
          ventaPersonalizacionOpcionCollection,
          ventaPersonalizacionOpcion
        );
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(ventaPersonalizacionOpcion);
      });

      it('should add only unique VentaPersonalizacionOpcion to an array', () => {
        const ventaPersonalizacionOpcionArray: IVentaPersonalizacionOpcion[] = [
          sampleWithRequiredData,
          sampleWithPartialData,
          sampleWithFullData,
        ];
        const ventaPersonalizacionOpcionCollection: IVentaPersonalizacionOpcion[] = [sampleWithRequiredData];
        expectedResult = service.addVentaPersonalizacionOpcionToCollectionIfMissing(
          ventaPersonalizacionOpcionCollection,
          ...ventaPersonalizacionOpcionArray
        );
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const ventaPersonalizacionOpcion: IVentaPersonalizacionOpcion = sampleWithRequiredData;
        const ventaPersonalizacionOpcion2: IVentaPersonalizacionOpcion = sampleWithPartialData;
        expectedResult = service.addVentaPersonalizacionOpcionToCollectionIfMissing(
          [],
          ventaPersonalizacionOpcion,
          ventaPersonalizacionOpcion2
        );
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(ventaPersonalizacionOpcion);
        expect(expectedResult).toContain(ventaPersonalizacionOpcion2);
      });

      it('should accept null and undefined values', () => {
        const ventaPersonalizacionOpcion: IVentaPersonalizacionOpcion = sampleWithRequiredData;
        expectedResult = service.addVentaPersonalizacionOpcionToCollectionIfMissing([], null, ventaPersonalizacionOpcion, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(ventaPersonalizacionOpcion);
      });

      it('should return initial array if no VentaPersonalizacionOpcion is added', () => {
        const ventaPersonalizacionOpcionCollection: IVentaPersonalizacionOpcion[] = [sampleWithRequiredData];
        expectedResult = service.addVentaPersonalizacionOpcionToCollectionIfMissing(ventaPersonalizacionOpcionCollection, undefined, null);
        expect(expectedResult).toEqual(ventaPersonalizacionOpcionCollection);
      });
    });

    describe('compareVentaPersonalizacionOpcion', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareVentaPersonalizacionOpcion(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareVentaPersonalizacionOpcion(entity1, entity2);
        const compareResult2 = service.compareVentaPersonalizacionOpcion(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareVentaPersonalizacionOpcion(entity1, entity2);
        const compareResult2 = service.compareVentaPersonalizacionOpcion(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareVentaPersonalizacionOpcion(entity1, entity2);
        const compareResult2 = service.compareVentaPersonalizacionOpcion(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
