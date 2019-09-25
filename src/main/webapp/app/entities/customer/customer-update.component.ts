import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ICustomer, Customer } from 'app/shared/model/customer.model';
import { CustomerService } from './customer.service';

@Component({
  selector: 'jhi-customer-update',
  templateUrl: './customer-update.component.html',
})
export class CustomerUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    firstName: [],
    lastName: [],
    gender: [],
    birthday: [],
    extraName: [],
    originAddress: [],
    nation: [],
    religion: [],
    nationality: [],
    indentity: [],
    regularlyAdress: [],
    address: [],
    academicLevel: [],
    qualification: [],
    foreignLevel: [],
    job: [],
    email: [],
    passsword: [],
    phone: [],
    status: [],
  });

  constructor(protected customerService: CustomerService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ customer }) => {
      this.updateForm(customer);
    });
  }

  updateForm(customer: ICustomer): void {
    this.editForm.patchValue({
      id: customer.id,
      firstName: customer.firstName,
      lastName: customer.lastName,
      gender: customer.gender,
      birthday: customer.birthday,
      extraName: customer.extraName,
      originAddress: customer.originAddress,
      nation: customer.nation,
      religion: customer.religion,
      nationality: customer.nationality,
      indentity: customer.indentity,
      regularlyAdress: customer.regularlyAdress,
      address: customer.address,
      academicLevel: customer.academicLevel,
      qualification: customer.qualification,
      foreignLevel: customer.foreignLevel,
      job: customer.job,
      email: customer.email,
      passsword: customer.passsword,
      phone: customer.phone,
      status: customer.status,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const customer = this.createFromForm();
    if (customer.id !== undefined) {
      this.subscribeToSaveResponse(this.customerService.update(customer));
    } else {
      this.subscribeToSaveResponse(this.customerService.create(customer));
    }
  }

  private createFromForm(): ICustomer {
    return {
      ...new Customer(),
      id: this.editForm.get(['id'])!.value,
      firstName: this.editForm.get(['firstName'])!.value,
      lastName: this.editForm.get(['lastName'])!.value,
      gender: this.editForm.get(['gender'])!.value,
      birthday: this.editForm.get(['birthday'])!.value,
      extraName: this.editForm.get(['extraName'])!.value,
      originAddress: this.editForm.get(['originAddress'])!.value,
      nation: this.editForm.get(['nation'])!.value,
      religion: this.editForm.get(['religion'])!.value,
      nationality: this.editForm.get(['nationality'])!.value,
      indentity: this.editForm.get(['indentity'])!.value,
      regularlyAdress: this.editForm.get(['regularlyAdress'])!.value,
      address: this.editForm.get(['address'])!.value,
      academicLevel: this.editForm.get(['academicLevel'])!.value,
      qualification: this.editForm.get(['qualification'])!.value,
      foreignLevel: this.editForm.get(['foreignLevel'])!.value,
      job: this.editForm.get(['job'])!.value,
      email: this.editForm.get(['email'])!.value,
      passsword: this.editForm.get(['passsword'])!.value,
      phone: this.editForm.get(['phone'])!.value,
      status: this.editForm.get(['status'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICustomer>>): void {
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
}
