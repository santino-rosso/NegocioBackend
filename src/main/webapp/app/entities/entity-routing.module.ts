import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'caracteristica',
        data: { pageTitle: 'negocioApp.caracteristica.home.title' },
        loadChildren: () => import('./caracteristica/caracteristica.module').then(m => m.CaracteristicaModule),
      },
      {
        path: 'adicional',
        data: { pageTitle: 'negocioApp.adicional.home.title' },
        loadChildren: () => import('./adicional/adicional.module').then(m => m.AdicionalModule),
      },
      {
        path: 'dispositivo',
        data: { pageTitle: 'negocioApp.dispositivo.home.title' },
        loadChildren: () => import('./dispositivo/dispositivo.module').then(m => m.DispositivoModule),
      },
      {
        path: 'personalizacion',
        data: { pageTitle: 'negocioApp.personalizacion.home.title' },
        loadChildren: () => import('./personalizacion/personalizacion.module').then(m => m.PersonalizacionModule),
      },
      {
        path: 'opcion',
        data: { pageTitle: 'negocioApp.opcion.home.title' },
        loadChildren: () => import('./opcion/opcion.module').then(m => m.OpcionModule),
      },
      {
        path: 'venta',
        data: { pageTitle: 'negocioApp.venta.home.title' },
        loadChildren: () => import('./venta/venta.module').then(m => m.VentaModule),
      },
      {
        path: 'venta-personalizacion-opcion',
        data: { pageTitle: 'negocioApp.ventaPersonalizacionOpcion.home.title' },
        loadChildren: () =>
          import('./venta-personalizacion-opcion/venta-personalizacion-opcion.module').then(m => m.VentaPersonalizacionOpcionModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class EntityRoutingModule {}
