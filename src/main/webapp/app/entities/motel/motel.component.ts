import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IMotel } from 'app/shared/model/motel.model';
import { MotelService } from './motel.service';
import { MotelDeleteDialogComponent } from './motel-delete-dialog.component';

@Component({
  selector: 'jhi-motel',
  templateUrl: './motel.component.html',
})
export class MotelComponent implements OnInit, OnDestroy {
  motels?: IMotel[];
  eventSubscriber?: Subscription;

  constructor(protected motelService: MotelService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.motelService.query().subscribe((res: HttpResponse<IMotel[]>) => (this.motels = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInMotels();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IMotel): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInMotels(): void {
    this.eventSubscriber = this.eventManager.subscribe('motelListModification', () => this.loadAll());
  }

  delete(motel: IMotel): void {
    const modalRef = this.modalService.open(MotelDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.motel = motel;
  }
}
