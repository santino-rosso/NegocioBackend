import dayjs from 'dayjs/esm';
import { IAdicional } from 'app/entities/adicional/adicional.model';
import { ICaracteristica } from 'app/entities/caracteristica/caracteristica.model';

export interface IVenta {
  id: number;
  idDispositivo?: number | null;
  codigo?: string | null;
  nombre?: string | null;
  descripcion?: string | null;
  precioBase?: number | null;
  moneda?: string | null;
  fechaVenta?: dayjs.Dayjs | null;
  precioFinal?: number | null;
  adicionales?: Pick<IAdicional, 'id'>[] | null;
  caracteristicas?: Pick<ICaracteristica, 'id'>[] | null;
}

export type NewVenta = Omit<IVenta, 'id'> & { id: null };
