import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IDispositivo } from '../dispositivo.model';
import { DispositivoService } from '../service/dispositivo.service';

@Injectable({ providedIn: 'root' })
export class DispositivoRoutingResolveService implements Resolve<IDispositivo | null> {
  constructor(protected service: DispositivoService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IDispositivo | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((dispositivo: HttpResponse<IDispositivo>) => {
          if (dispositivo.body) {
            return of(dispositivo.body);
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
