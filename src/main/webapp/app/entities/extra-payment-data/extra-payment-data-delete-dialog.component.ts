import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IExtraPaymentData } from 'app/shared/model/extra-payment-data.model';
import { ExtraPaymentDataService } from './extra-payment-data.service';

@Component({
  templateUrl: './extra-payment-data-delete-dialog.component.html',
})
export class ExtraPaymentDataDeleteDialogComponent {
  extraPaymentData?: IExtraPaymentData;

  constructor(
    protected extraPaymentDataService: ExtraPaymentDataService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.extraPaymentDataService.delete(id).subscribe(() => {
      this.eventManager.broadcast('extraPaymentDataListModification');
      this.activeModal.close();
    });
  }
}
