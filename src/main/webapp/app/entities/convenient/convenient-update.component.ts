import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IConvenient, Convenient } from 'app/shared/model/convenient.model';
import { ConvenientService } from './convenient.service';
import { IMotel } from 'app/shared/model/motel.model';
import { MotelService } from 'app/entities/motel/motel.service';
import { IRoom } from 'app/shared/model/room.model';
import { RoomService } from 'app/entities/room/room.service';

type SelectableEntity = IMotel | IRoom;

@Component({
  selector: 'jhi-convenient-update',
  templateUrl: './convenient-update.component.html',
})
export class ConvenientUpdateComponent implements OnInit {
  isSaving = false;
  motels: IMotel[] = [];
  rooms: IRoom[] = [];

  editForm = this.fb.group({
    id: [],
    title: [],
    decription: [],
    motel: [],
    room: [],
  });

  constructor(
    protected convenientService: ConvenientService,
    protected motelService: MotelService,
    protected roomService: RoomService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ convenient }) => {
      this.updateForm(convenient);

      this.motelService.query().subscribe((res: HttpResponse<IMotel[]>) => (this.motels = res.body || []));

      this.roomService.query().subscribe((res: HttpResponse<IRoom[]>) => (this.rooms = res.body || []));
    });
  }

  updateForm(convenient: IConvenient): void {
    this.editForm.patchValue({
      id: convenient.id,
      title: convenient.title,
      decription: convenient.decription,
      motel: convenient.motel,
      room: convenient.room,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const convenient = this.createFromForm();
    if (convenient.id !== undefined) {
      this.subscribeToSaveResponse(this.convenientService.update(convenient));
    } else {
      this.subscribeToSaveResponse(this.convenientService.create(convenient));
    }
  }

  private createFromForm(): IConvenient {
    return {
      ...new Convenient(),
      id: this.editForm.get(['id'])!.value,
      title: this.editForm.get(['title'])!.value,
      decription: this.editForm.get(['decription'])!.value,
      motel: this.editForm.get(['motel'])!.value,
      room: this.editForm.get(['room'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IConvenient>>): void {
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

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }
}
