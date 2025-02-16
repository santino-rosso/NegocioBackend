import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { VentaPersonalizacionOpcionComponent } from './list/venta-personalizacion-opcion.component';
import { VentaPersonalizacionOpcionDetailComponent } from './detail/venta-personalizacion-opcion-detail.component';
import { VentaPersonalizacionOpcionUpdateComponent } from './update/venta-personalizacion-opcion-update.component';
import { VentaPersonalizacionOpcionDeleteDialogComponent } from './delete/venta-personalizacion-opcion-delete-dialog.component';
import { VentaPersonalizacionOpcionRoutingModule } from './route/venta-personalizacion-opcion-routing.module';

@NgModule({
  imports: [SharedModule, VentaPersonalizacionOpcionRoutingModule],
  declarations: [
    VentaPersonalizacionOpcionComponent,
    VentaPersonalizacionOpcionDetailComponent,
    VentaPersonalizacionOpcionUpdateComponent,
    VentaPersonalizacionOpcionDeleteDialogComponent,
  ],
})
export class VentaPersonalizacionOpcionModule {}
