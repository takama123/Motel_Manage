import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IContract } from 'app/shared/model/contract.model';
import { ContractService } from './contract.service';
import { ContractDeleteDialogComponent } from './contract-delete-dialog.component';

@Component({
  selector: 'jhi-contract',
  templateUrl: './contract.component.html',
})
export class ContractComponent implements OnInit, OnDestroy {
  contracts?: IContract[];
  eventSubscriber?: Subscription;

  constructor(protected contractService: ContractService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.contractService.query().subscribe((res: HttpResponse<IContract[]>) => (this.contracts = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInContracts();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IContract): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInContracts(): void {
    this.eventSubscriber = this.eventManager.subscribe('contractListModification', () => this.loadAll());
  }

  delete(contract: IContract): void {
    const modalRef = this.modalService.open(ContractDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.contract = contract;
  }
}
