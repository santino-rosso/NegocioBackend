import { IVentaPersonalizacionOpcion, NewVentaPersonalizacionOpcion } from './venta-personalizacion-opcion.model';

export const sampleWithRequiredData: IVentaPersonalizacionOpcion = {
  id: 69516,
};

export const sampleWithPartialData: IVentaPersonalizacionOpcion = {
  id: 60942,
};

export const sampleWithFullData: IVentaPersonalizacionOpcion = {
  id: 79571,
};

export const sampleWithNewData: NewVentaPersonalizacionOpcion = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
