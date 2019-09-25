import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IRoom, Room } from 'app/shared/model/room.model';
import { RoomService } from './room.service';
import { IMotel } from 'app/shared/model/motel.model';
import { MotelService } from 'app/entities/motel/motel.service';

@Component({
  selector: 'jhi-room-update',
  templateUrl: './room-update.component.html',
})
export class RoomUpdateComponent implements OnInit {
  isSaving = false;
  motels: IMotel[] = [];

  editForm = this.fb.group({
    id: [],
    title: [],
    price: [],
    status: [],
    acreage: [],
    decription: [],
    motel: [],
  });

  constructor(
    protected roomService: RoomService,
    protected motelService: MotelService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ room }) => {
      this.updateForm(room);

      this.motelService.query().subscribe((res: HttpResponse<IMotel[]>) => (this.motels = res.body || []));
    });
  }

  updateForm(room: IRoom): void {
    this.editForm.patchValue({
      id: room.id,
      title: room.title,
      price: room.price,
      status: room.status,
      acreage: room.acreage,
      decription: room.decription,
      motel: room.motel,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const room = this.createFromForm();
    if (room.id !== undefined) {
      this.subscribeToSaveResponse(this.roomService.update(room));
    } else {
      this.subscribeToSaveResponse(this.roomService.create(room));
    }
  }

  private createFromForm(): IRoom {
    return {
      ...new Room(),
      id: this.editForm.get(['id'])!.value,
      title: this.editForm.get(['title'])!.value,
      price: this.editForm.get(['price'])!.value,
      status: this.editForm.get(['status'])!.value,
      acreage: this.editForm.get(['acreage'])!.value,
      decription: this.editForm.get(['decription'])!.value,
      motel: this.editForm.get(['motel'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IRoom>>): void {
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

  trackById(index: number, item: IMotel): any {
    return item.id;
  }
}
