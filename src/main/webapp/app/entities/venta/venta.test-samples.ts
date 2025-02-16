import dayjs from 'dayjs/esm';

import { IVenta, NewVenta } from './venta.model';

export const sampleWithRequiredData: IVenta = {
  id: 20448,
  idDispositivo: 11440,
  codigo: 'array Oklahoma initiatives',
  nombre: 'Programmable directional Unit',
  descripcion: 'Organized',
  precioBase: 22012,
  moneda: 'Metal Focused Berkshire',
  fechaVenta: dayjs('2024-11-25T12:10'),
};

export const sampleWithPartialData: IVenta = {
  id: 36457,
  idDispositivo: 17394,
  codigo: 'Creative overriding content',
  nombre: 'SQL Incredible',
  descripcion: 'Outdoors Developer',
  precioBase: 47568,
  moneda: 'Arabia',
  fechaVenta: dayjs('2024-11-25T16:00'),
  precioFinal: 6042,
};

export const sampleWithFullData: IVenta = {
  id: 76466,
  idDispositivo: 69697,
  codigo: 'Account Computers back-end',
  nombre: 'Canada sky Corners',
  descripcion: '1080p impactful',
  precioBase: 94259,
  moneda: 'Universal',
  fechaVenta: dayjs('2024-11-25T18:16'),
  precioFinal: 25605,
};

export const sampleWithNewData: NewVenta = {
  idDispositivo: 86870,
  codigo: 'Fiji Estates green',
  nombre: 'Italy',
  descripcion: 'programming Future-proofed',
  precioBase: 9348,
  moneda: 'bluetooth Pound',
  fechaVenta: dayjs('2024-11-25T17:55'),
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
