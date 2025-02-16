import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IVentaPersonalizacionOpcion } from '../venta-personalizacion-opcion.model';
import { VentaPersonalizacionOpcionService } from '../service/venta-personalizacion-opcion.service';

@Injectable({ providedIn: 'root' })
export class VentaPersonalizacionOpcionRoutingResolveService implements Resolve<IVentaPersonalizacionOpcion | null> {
  constructor(protected service: VentaPersonalizacionOpcionService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IVentaPersonalizacionOpcion | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((ventaPersonalizacionOpcion: HttpResponse<IVentaPersonalizacionOpcion>) => {
          if (ventaPersonalizacionOpcion.body) {
            return of(ventaPersonalizacionOpcion.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(null);
  }
}
