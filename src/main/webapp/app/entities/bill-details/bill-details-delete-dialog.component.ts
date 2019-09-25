import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IBillDetails } from 'app/shared/model/bill-details.model';
import { BillDetailsService } from './bill-details.service';

@Component({
  templateUrl: './bill-details-delete-dialog.component.html',
})
export class BillDetailsDeleteDialogComponent {
  billDetails?: IBillDetails;

  constructor(
    protected billDetailsService: BillDetailsService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.billDetailsService.delete(id).subscribe(() => {
      this.eventManager.broadcast('billDetailsListModification');
      this.activeModal.close();
    });
  }
}
