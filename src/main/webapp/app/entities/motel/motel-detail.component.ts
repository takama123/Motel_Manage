import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMotel } from 'app/shared/model/motel.model';

@Component({
  selector: 'jhi-motel-detail',
  templateUrl: './motel-detail.component.html',
})
export class MotelDetailComponent implements OnInit {
  motel: IMotel | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ motel }) => (this.motel = motel));
  }

  previousState(): void {
    window.history.back();
  }
}
