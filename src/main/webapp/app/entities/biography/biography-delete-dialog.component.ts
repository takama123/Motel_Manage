import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IBiography } from 'app/shared/model/biography.model';
import { BiographyService } from './biography.service';

@Component({
  templateUrl: './biography-delete-dialog.component.html',
})
export class BiographyDeleteDialogComponent {
  biography?: IBiography;

  constructor(protected biographyService: BiographyService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.biographyService.delete(id).subscribe(() => {
      this.eventManager.broadcast('biographyListModification');
      this.activeModal.close();
    });
  }
}
