import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { VentaPersonalizacionOpcionComponent } from '../list/venta-personalizacion-opcion.component';
import { VentaPersonalizacionOpcionDetailComponent } from '../detail/venta-personalizacion-opcion-detail.component';
import { VentaPersonalizacionOpcionUpdateComponent } from '../update/venta-personalizacion-opcion-update.component';
import { VentaPersonalizacionOpcionRoutingResolveService } from './venta-personalizacion-opcion-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const ventaPersonalizacionOpcionRoute: Routes = [
  {
    path: '',
    component: VentaPersonalizacionOpcionComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: VentaPersonalizacionOpcionDetailComponent,
    resolve: {
      ventaPersonalizacionOpcion: VentaPersonalizacionOpcionRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: VentaPersonalizacionOpcionUpdateComponent,
    resolve: {
      ventaPersonalizacionOpcion: VentaPersonalizacionOpcionRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: VentaPersonalizacionOpcionUpdateComponent,
    resolve: {
      ventaPersonalizacionOpcion: VentaPersonalizacionOpcionRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(ventaPersonalizacionOpcionRoute)],
  exports: [RouterModule],
})
export class VentaPersonalizacionOpcionRoutingModule {}
