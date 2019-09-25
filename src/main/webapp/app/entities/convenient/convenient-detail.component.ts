import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IConvenient } from 'app/shared/model/convenient.model';

@Component({
  selector: 'jhi-convenient-detail',
  templateUrl: './convenient-detail.component.html',
})
export class ConvenientDetailComponent implements OnInit {
  convenient: IConvenient | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ convenient }) => (this.convenient = convenient));
  }

  previousState(): void {
    window.history.back();
  }
}
