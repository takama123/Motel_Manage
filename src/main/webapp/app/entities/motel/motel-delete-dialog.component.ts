import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMotel } from 'app/shared/model/motel.model';
import { MotelService } from './motel.service';

@Component({
  templateUrl: './motel-delete-dialog.component.html',
})
export class MotelDeleteDialogComponent {
  motel?: IMotel;

  constructor(protected motelService: MotelService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.motelService.delete(id).subscribe(() => {
      this.eventManager.broadcast('motelListModification');
      this.activeModal.close();
    });
  }
}
