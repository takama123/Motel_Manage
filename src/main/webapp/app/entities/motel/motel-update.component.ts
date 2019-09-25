import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IMotel, Motel } from 'app/shared/model/motel.model';
import { MotelService } from './motel.service';

@Component({
  selector: 'jhi-motel-update',
  templateUrl: './motel-update.component.html',
})
export class MotelUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    title: [],
    address: [],
    phone: [],
    decription: [],
    electricityPrice: [],
    waterPrice: [],
  });

  constructor(protected motelService: MotelService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ motel }) => {
      this.updateForm(motel);
    });
  }

  updateForm(motel: IMotel): void {
    this.editForm.patchValue({
      id: motel.id,
      title: motel.title,
      address: motel.address,
      phone: motel.phone,
      decription: motel.decription,
      electricityPrice: motel.electricityPrice,
      waterPrice: motel.waterPrice,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const motel = this.createFromForm();
    if (motel.id !== undefined) {
      this.subscribeToSaveResponse(this.motelService.update(motel));
    } else {
      this.subscribeToSaveResponse(this.motelService.create(motel));
    }
  }

  private createFromForm(): IMotel {
    return {
      ...new Motel(),
      id: this.editForm.get(['id'])!.value,
      title: this.editForm.get(['title'])!.value,
      address: this.editForm.get(['address'])!.value,
      phone: this.editForm.get(['phone'])!.value,
      decription: this.editForm.get(['decription'])!.value,
      electricityPrice: this.editForm.get(['electricityPrice'])!.value,
      waterPrice: this.editForm.get(['waterPrice'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMotel>>): void {
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
