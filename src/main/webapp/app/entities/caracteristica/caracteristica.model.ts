import { IDispositivo } from 'app/entities/dispositivo/dispositivo.model';
import { IVenta } from 'app/entities/venta/venta.model';

export interface ICaracteristica {
  id: number;
  nombre?: string | null;
  descripcion?: string | null;
  dispositivo?: Pick<IDispositivo, 'id'> | null;
  ventas?: Pick<IVenta, 'id'>[] | null;
}

export type NewCaracteristica = Omit<ICaracteristica, 'id'> & { id: null };
