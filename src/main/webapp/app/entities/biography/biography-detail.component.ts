import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IBiography } from 'app/shared/model/biography.model';

@Component({
  selector: 'jhi-biography-detail',
  templateUrl: './biography-detail.component.html',
})
export class BiographyDetailComponent implements OnInit {
  biography: IBiography | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ biography }) => (this.biography = biography));
  }

  previousState(): void {
    window.history.back();
  }
}
