import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IVentaPersonalizacionOpcion } from '../venta-personalizacion-opcion.model';
import { VentaPersonalizacionOpcionService } from '../service/venta-personalizacion-opcion.service';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';

@Component({
  templateUrl: './venta-personalizacion-opcion-delete-dialog.component.html',
})
export class VentaPersonalizacionOpcionDeleteDialogComponent {
  ventaPersonalizacionOpcion?: IVentaPersonalizacionOpcion;

  constructor(protected ventaPersonalizacionOpcionService: VentaPersonalizacionOpcionService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.ventaPersonalizacionOpcionService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
