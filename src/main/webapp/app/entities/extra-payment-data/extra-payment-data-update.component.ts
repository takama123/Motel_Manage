import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IExtraPaymentData, ExtraPaymentData } from 'app/shared/model/extra-payment-data.model';
import { ExtraPaymentDataService } from './extra-payment-data.service';
import { IBillDetails } from 'app/shared/model/bill-details.model';
import { BillDetailsService } from 'app/entities/bill-details/bill-details.service';

@Component({
  selector: 'jhi-extra-payment-data-update',
  templateUrl: './extra-payment-data-update.component.html',
})
export class ExtraPaymentDataUpdateComponent implements OnInit {
  isSaving = false;
  billdetails: IBillDetails[] = [];

  editForm = this.fb.group({
    id: [],
    cost: [],
    decription: [],
    billDetails: [],
  });

  constructor(
    protected extraPaymentDataService: ExtraPaymentDataService,
    protected billDetailsService: BillDetailsService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ extraPaymentData }) => {
      this.updateForm(extraPaymentData);

      this.billDetailsService.query().subscribe((res: HttpResponse<IBillDetails[]>) => (this.billdetails = res.body || []));
    });
  }

  updateForm(extraPaymentData: IExtraPaymentData): void {
    this.editForm.patchValue({
      id: extraPaymentData.id,
      cost: extraPaymentData.cost,
      decription: extraPaymentData.decription,
      billDetails: extraPaymentData.billDetails,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const extraPaymentData = this.createFromForm();
    if (extraPaymentData.id !== undefined) {
      this.subscribeToSaveResponse(this.extraPaymentDataService.update(extraPaymentData));
    } else {
      this.subscribeToSaveResponse(this.extraPaymentDataService.create(extraPaymentData));
    }
  }

  private createFromForm(): IExtraPaymentData {
    return {
      ...new ExtraPaymentData(),
      id: this.editForm.get(['id'])!.value,
      cost: this.editForm.get(['cost'])!.value,
      decription: this.editForm.get(['decription'])!.value,
      billDetails: this.editForm.get(['billDetails'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IExtraPaymentData>>): void {
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

  trackById(index: number, item: IBillDetails): any {
    return item.id;
  }
}
