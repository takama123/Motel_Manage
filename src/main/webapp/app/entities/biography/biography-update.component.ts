import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IBiography, Biography } from 'app/shared/model/biography.model';
import { BiographyService } from './biography.service';
import { ICustomer } from 'app/shared/model/customer.model';
import { CustomerService } from 'app/entities/customer/customer.service';

@Component({
  selector: 'jhi-biography-update',
  templateUrl: './biography-update.component.html',
})
export class BiographyUpdateComponent implements OnInit {
  isSaving = false;
  customers: ICustomer[] = [];
  fromDateDp: any;
  toDateDp: any;

  editForm = this.fb.group({
    id: [],
    fromDate: [],
    toDate: [],
    address: [],
    workingDecription: [],
    customer: [],
  });

  constructor(
    protected biographyService: BiographyService,
    protected customerService: CustomerService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ biography }) => {
      this.updateForm(biography);

      this.customerService.query().subscribe((res: HttpResponse<ICustomer[]>) => (this.customers = res.body || []));
    });
  }

  updateForm(biography: IBiography): void {
    this.editForm.patchValue({
      id: biography.id,
      fromDate: biography.fromDate,
      toDate: biography.toDate,
      address: biography.address,
      workingDecription: biography.workingDecription,
      customer: biography.customer,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const biography = this.createFromForm();
    if (biography.id !== undefined) {
      this.subscribeToSaveResponse(this.biographyService.update(biography));
    } else {
      this.subscribeToSaveResponse(this.biographyService.create(biography));
    }
  }

  private createFromForm(): IBiography {
    return {
      ...new Biography(),
      id: this.editForm.get(['id'])!.value,
      fromDate: this.editForm.get(['fromDate'])!.value,
      toDate: this.editForm.get(['toDate'])!.value,
      address: this.editForm.get(['address'])!.value,
      workingDecription: this.editForm.get(['workingDecription'])!.value,
      customer: this.editForm.get(['customer'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IBiography>>): void {
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

  trackById(index: number, item: ICustomer): any {
    return item.id;
  }
}
