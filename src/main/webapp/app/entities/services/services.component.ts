import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IServices } from 'app/shared/model/services.model';
import { ServicesService } from './services.service';
import { ServicesDeleteDialogComponent } from './services-delete-dialog.component';

@Component({
  selector: 'jhi-services',
  templateUrl: './services.component.html',
})
export class ServicesComponent implements OnInit, OnDestroy {
  services?: IServices[];
  eventSubscriber?: Subscription;

  constructor(protected servicesService: ServicesService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.servicesService.query().subscribe((res: HttpResponse<IServices[]>) => (this.services = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInServices();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IServices): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInServices(): void {
    this.eventSubscriber = this.eventManager.subscribe('servicesListModification', () => this.loadAll());
  }

  delete(services: IServices): void {
    const modalRef = this.modalService.open(ServicesDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.services = services;
  }
}
