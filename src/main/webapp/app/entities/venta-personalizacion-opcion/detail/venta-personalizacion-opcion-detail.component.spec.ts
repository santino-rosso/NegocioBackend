import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { VentaPersonalizacionOpcionDetailComponent } from './venta-personalizacion-opcion-detail.component';

describe('VentaPersonalizacionOpcion Management Detail Component', () => {
  let comp: VentaPersonalizacionOpcionDetailComponent;
  let fixture: ComponentFixture<VentaPersonalizacionOpcionDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [VentaPersonalizacionOpcionDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ ventaPersonalizacionOpcion: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(VentaPersonalizacionOpcionDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(VentaPersonalizacionOpcionDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load ventaPersonalizacionOpcion on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.ventaPersonalizacionOpcion).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
