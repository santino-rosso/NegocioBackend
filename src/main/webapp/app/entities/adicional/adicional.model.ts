import { IDispositivo } from 'app/entities/dispositivo/dispositivo.model';
import { IVenta } from 'app/entities/venta/venta.model';

export interface IAdicional {
  id: number;
  nombre?: string | null;
  descripcion?: string | null;
  precio?: number | null;
  precioGratis?: number | null;
  dispositivos?: Pick<IDispositivo, 'id'>[] | null;
  ventas?: Pick<IVenta, 'id'>[] | null;
}

export type NewAdicional = Omit<IAdicional, 'id'> & { id: null };
