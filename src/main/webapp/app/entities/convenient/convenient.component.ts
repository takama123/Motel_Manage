import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IConvenient } from 'app/shared/model/convenient.model';
import { ConvenientService } from './convenient.service';
import { ConvenientDeleteDialogComponent } from './convenient-delete-dialog.component';

@Component({
  selector: 'jhi-convenient',
  templateUrl: './convenient.component.html',
})
export class ConvenientComponent implements OnInit, OnDestroy {
  convenients?: IConvenient[];
  eventSubscriber?: Subscription;

  constructor(protected convenientService: ConvenientService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.convenientService.query().subscribe((res: HttpResponse<IConvenient[]>) => (this.convenients = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInConvenients();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IConvenient): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInConvenients(): void {
    this.eventSubscriber = this.eventManager.subscribe('convenientListModification', () => this.loadAll());
  }

  delete(convenient: IConvenient): void {
    const modalRef = this.modalService.open(ConvenientDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.convenient = convenient;
  }
}
