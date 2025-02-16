import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IVentaPersonalizacionOpcion, NewVentaPersonalizacionOpcion } from '../venta-personalizacion-opcion.model';

export type PartialUpdateVentaPersonalizacionOpcion = Partial<IVentaPersonalizacionOpcion> & Pick<IVentaPersonalizacionOpcion, 'id'>;

export type EntityResponseType = HttpResponse<IVentaPersonalizacionOpcion>;
export type EntityArrayResponseType = HttpResponse<IVentaPersonalizacionOpcion[]>;

@Injectable({ providedIn: 'root' })
export class VentaPersonalizacionOpcionService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/venta-personalizacion-opcions');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(ventaPersonalizacionOpcion: NewVentaPersonalizacionOpcion): Observable<EntityResponseType> {
    return this.http.post<IVentaPersonalizacionOpcion>(this.resourceUrl, ventaPersonalizacionOpcion, { observe: 'response' });
  }

  update(ventaPersonalizacionOpcion: IVentaPersonalizacionOpcion): Observable<EntityResponseType> {
    return this.http.put<IVentaPersonalizacionOpcion>(
      `${this.resourceUrl}/${this.getVentaPersonalizacionOpcionIdentifier(ventaPersonalizacionOpcion)}`,
      ventaPersonalizacionOpcion,
      { observe: 'response' }
    );
  }

  partialUpdate(ventaPersonalizacionOpcion: PartialUpdateVentaPersonalizacionOpcion): Observable<EntityResponseType> {
    return this.http.patch<IVentaPersonalizacionOpcion>(
      `${this.resourceUrl}/${this.getVentaPersonalizacionOpcionIdentifier(ventaPersonalizacionOpcion)}`,
      ventaPersonalizacionOpcion,
      { observe: 'response' }
    );
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IVentaPersonalizacionOpcion>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IVentaPersonalizacionOpcion[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getVentaPersonalizacionOpcionIdentifier(ventaPersonalizacionOpcion: Pick<IVentaPersonalizacionOpcion, 'id'>): number {
    return ventaPersonalizacionOpcion.id;
  }

  compareVentaPersonalizacionOpcion(
    o1: Pick<IVentaPersonalizacionOpcion, 'id'> | null,
    o2: Pick<IVentaPersonalizacionOpcion, 'id'> | null
  ): boolean {
    return o1 && o2 ? this.getVentaPersonalizacionOpcionIdentifier(o1) === this.getVentaPersonalizacionOpcionIdentifier(o2) : o1 === o2;
  }

  addVentaPersonalizacionOpcionToCollectionIfMissing<Type extends Pick<IVentaPersonalizacionOpcion, 'id'>>(
    ventaPersonalizacionOpcionCollection: Type[],
    ...ventaPersonalizacionOpcionsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const ventaPersonalizacionOpcions: Type[] = ventaPersonalizacionOpcionsToCheck.filter(isPresent);
    if (ventaPersonalizacionOpcions.length > 0) {
      const ventaPersonalizacionOpcionCollectionIdentifiers = ventaPersonalizacionOpcionCollection.map(
        ventaPersonalizacionOpcionItem => this.getVentaPersonalizacionOpcionIdentifier(ventaPersonalizacionOpcionItem)!
      );
      const ventaPersonalizacionOpcionsToAdd = ventaPersonalizacionOpcions.filter(ventaPersonalizacionOpcionItem => {
        const ventaPersonalizacionOpcionIdentifier = this.getVentaPersonalizacionOpcionIdentifier(ventaPersonalizacionOpcionItem);
        if (ventaPersonalizacionOpcionCollectionIdentifiers.includes(ventaPersonalizacionOpcionIdentifier)) {
          return false;
        }
        ventaPersonalizacionOpcionCollectionIdentifiers.push(ventaPersonalizacionOpcionIdentifier);
        return true;
      });
      return [...ventaPersonalizacionOpcionsToAdd, ...ventaPersonalizacionOpcionCollection];
    }
    return ventaPersonalizacionOpcionCollection;
  }
}
