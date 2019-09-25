import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IExtraPaymentData } from 'app/shared/model/extra-payment-data.model';
import { ExtraPaymentDataService } from './extra-payment-data.service';
import { ExtraPaymentDataDeleteDialogComponent } from './extra-payment-data-delete-dialog.component';

@Component({
  selector: 'jhi-extra-payment-data',
  templateUrl: './extra-payment-data.component.html',
})
export class ExtraPaymentDataComponent implements OnInit, OnDestroy {
  extraPaymentData?: IExtraPaymentData[];
  eventSubscriber?: Subscription;

  constructor(
    protected extraPaymentDataService: ExtraPaymentDataService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.extraPaymentDataService.query().subscribe((res: HttpResponse<IExtraPaymentData[]>) => (this.extraPaymentData = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInExtraPaymentData();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IExtraPaymentData): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInExtraPaymentData(): void {
    this.eventSubscriber = this.eventManager.subscribe('extraPaymentDataListModification', () => this.loadAll());
  }

  delete(extraPaymentData: IExtraPaymentData): void {
    const modalRef = this.modalService.open(ExtraPaymentDataDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.extraPaymentData = extraPaymentData;
  }
}
