import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IBillDetails } from 'app/shared/model/bill-details.model';
import { BillDetailsService } from './bill-details.service';
import { BillDetailsDeleteDialogComponent } from './bill-details-delete-dialog.component';

@Component({
  selector: 'jhi-bill-details',
  templateUrl: './bill-details.component.html',
})
export class BillDetailsComponent implements OnInit, OnDestroy {
  billDetails?: IBillDetails[];
  eventSubscriber?: Subscription;

  constructor(
    protected billDetailsService: BillDetailsService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.billDetailsService.query().subscribe((res: HttpResponse<IBillDetails[]>) => (this.billDetails = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInBillDetails();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IBillDetails): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInBillDetails(): void {
    this.eventSubscriber = this.eventManager.subscribe('billDetailsListModification', () => this.loadAll());
  }

  delete(billDetails: IBillDetails): void {
    const modalRef = this.modalService.open(BillDetailsDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.billDetails = billDetails;
  }
}
