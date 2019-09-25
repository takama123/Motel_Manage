import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IExtraPaymentData } from 'app/shared/model/extra-payment-data.model';

@Component({
  selector: 'jhi-extra-payment-data-detail',
  templateUrl: './extra-payment-data-detail.component.html',
})
export class ExtraPaymentDataDetailComponent implements OnInit {
  extraPaymentData: IExtraPaymentData | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ extraPaymentData }) => (this.extraPaymentData = extraPaymentData));
  }

  previousState(): void {
    window.history.back();
  }
}
