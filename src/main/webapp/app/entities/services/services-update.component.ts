import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IServices, Services } from 'app/shared/model/services.model';
import { ServicesService } from './services.service';
import { IMotel } from 'app/shared/model/motel.model';
import { MotelService } from 'app/entities/motel/motel.service';
import { IRoom } from 'app/shared/model/room.model';
import { RoomService } from 'app/entities/room/room.service';

type SelectableEntity = IMotel | IRoom;

@Component({
  selector: 'jhi-services-update',
  templateUrl: './services-update.component.html',
})
export class ServicesUpdateComponent implements OnInit {
  isSaving = false;
  motels: IMotel[] = [];
  rooms: IRoom[] = [];

  editForm = this.fb.group({
    id: [],
    title: [],
    price: [],
    decription: [],
    motel: [],
    room: [],
  });

  constructor(
    protected servicesService: ServicesService,
    protected motelService: MotelService,
    protected roomService: RoomService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ services }) => {
      this.updateForm(services);

      this.motelService.query().subscribe((res: HttpResponse<IMotel[]>) => (this.motels = res.body || []));

      this.roomService.query().subscribe((res: HttpResponse<IRoom[]>) => (this.rooms = res.body || []));
    });
  }

  updateForm(services: IServices): void {
    this.editForm.patchValue({
      id: services.id,
      title: services.title,
      price: services.price,
      decription: services.decription,
      motel: services.motel,
      room: services.room,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const services = this.createFromForm();
    if (services.id !== undefined) {
      this.subscribeToSaveResponse(this.servicesService.update(services));
    } else {
      this.subscribeToSaveResponse(this.servicesService.create(services));
    }
  }

  private createFromForm(): IServices {
    return {
      ...new Services(),
      id: this.editForm.get(['id'])!.value,
      title: this.editForm.get(['title'])!.value,
      price: this.editForm.get(['price'])!.value,
      decription: this.editForm.get(['decription'])!.value,
      motel: this.editForm.get(['motel'])!.value,
      room: this.editForm.get(['room'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IServices>>): void {
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
