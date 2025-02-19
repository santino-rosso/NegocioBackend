import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'jhi-footer',
  templateUrl: './footer.component.html',
})
export class FooterComponent {
  constructor(private router: Router) {}
  isActivationPage(): boolean {
    return this.router.url.includes('/account/activate');
  }
}
