import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IConvenient } from 'app/shared/model/convenient.model';
import { ConvenientService } from './convenient.service';

@Component({
  templateUrl: './convenient-delete-dialog.component.html',
})
export class ConvenientDeleteDialogComponent {
  convenient?: IConvenient;

  constructor(
    protected convenientService: ConvenientService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.convenientService.delete(id).subscribe(() => {
      this.eventManager.broadcast('convenientListModification');
      this.activeModal.close();
    });
  }
}
