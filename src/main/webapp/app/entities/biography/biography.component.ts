import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IBiography } from 'app/shared/model/biography.model';
import { BiographyService } from './biography.service';
import { BiographyDeleteDialogComponent } from './biography-delete-dialog.component';

@Component({
  selector: 'jhi-biography',
  templateUrl: './biography.component.html',
})
export class BiographyComponent implements OnInit, OnDestroy {
  biographies?: IBiography[];
  eventSubscriber?: Subscription;

  constructor(protected biographyService: BiographyService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.biographyService.query().subscribe((res: HttpResponse<IBiography[]>) => (this.biographies = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInBiographies();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IBiography): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInBiographies(): void {
    this.eventSubscriber = this.eventManager.subscribe('biographyListModification', () => this.loadAll());
  }

  delete(biography: IBiography): void {
    const modalRef = this.modalService.open(BiographyDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.biography = biography;
  }
}
