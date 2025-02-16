import { IOpcion } from 'app/entities/opcion/opcion.model';
import { IPersonalizacion } from 'app/entities/personalizacion/personalizacion.model';
import { IVenta } from 'app/entities/venta/venta.model';

export interface IVentaPersonalizacionOpcion {
  id: number;
  opcion?: Pick<IOpcion, 'id'> | null;
  personalizacion?: Pick<IPersonalizacion, 'id'> | null;
  venta?: Pick<IVenta, 'id'> | null;
}

export type NewVentaPersonalizacionOpcion = Omit<IVentaPersonalizacionOpcion, 'id'> & { id: null };
