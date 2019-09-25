import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IBillDetails, BillDetails } from 'app/shared/model/bill-details.model';
import { BillDetailsService } from './bill-details.service';
import { IContract } from 'app/shared/model/contract.model';
import { ContractService } from 'app/entities/contract/contract.service';

@Component({
  selector: 'jhi-bill-details-update',
  templateUrl: './bill-details-update.component.html',
})
export class BillDetailsUpdateComponent implements OnInit {
  isSaving = false;
  contracts: IContract[] = [];
  startDateDp: any;
  endDateDp: any;

  editForm = this.fb.group({
    id: [],
    startDate: [],
    endDate: [],
    oldKwh: [],
    oldWater: [],
    newKwh: [],
    newWater: [],
    roomPrice: [],
    electricityPrice: [],
    waterPrice: [],
    contract: [],
  });

  constructor(
    protected billDetailsService: BillDetailsService,
    protected contractService: ContractService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ billDetails }) => {
      this.updateForm(billDetails);

      this.contractService.query().subscribe((res: HttpResponse<IContract[]>) => (this.contracts = res.body || []));
    });
  }

  updateForm(billDetails: IBillDetails): void {
    this.editForm.patchValue({
      id: billDetails.id,
      startDate: billDetails.startDate,
      endDate: billDetails.endDate,
      oldKwh: billDetails.oldKwh,
      oldWater: billDetails.oldWater,
      newKwh: billDetails.newKwh,
      newWater: billDetails.newWater,
      roomPrice: billDetails.roomPrice,
      electricityPrice: billDetails.electricityPrice,
      waterPrice: billDetails.waterPrice,
      contract: billDetails.contract,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const billDetails = this.createFromForm();
    if (billDetails.id !== undefined) {
      this.subscribeToSaveResponse(this.billDetailsService.update(billDetails));
    } else {
      this.subscribeToSaveResponse(this.billDetailsService.create(billDetails));
    }
  }

  private createFromForm(): IBillDetails {
    return {
      ...new BillDetails(),
      id: this.editForm.get(['id'])!.value,
      startDate: this.editForm.get(['startDate'])!.value,
      endDate: this.editForm.get(['endDate'])!.value,
      oldKwh: this.editForm.get(['oldKwh'])!.value,
      oldWater: this.editForm.get(['oldWater'])!.value,
      newKwh: this.editForm.get(['newKwh'])!.value,
      newWater: this.editForm.get(['newWater'])!.value,
      roomPrice: this.editForm.get(['roomPrice'])!.value,
      electricityPrice: this.editForm.get(['electricityPrice'])!.value,
      waterPrice: this.editForm.get(['waterPrice'])!.value,
      contract: this.editForm.get(['contract'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IBillDetails>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: IContract): any {
    return item.id;
  }
}
