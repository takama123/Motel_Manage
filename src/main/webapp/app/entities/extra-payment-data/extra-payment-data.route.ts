import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IExtraPaymentData, ExtraPaymentData } from 'app/shared/model/extra-payment-data.model';
import { ExtraPaymentDataService } from './extra-payment-data.service';
import { ExtraPaymentDataComponent } from './extra-payment-data.component';
import { ExtraPaymentDataDetailComponent } from './extra-payment-data-detail.component';
import { ExtraPaymentDataUpdateComponent } from './extra-payment-data-update.component';

@Injectable({ providedIn: 'root' })
export class ExtraPaymentDataResolve implements Resolve<IExtraPaymentData> {
  constructor(private service: ExtraPaymentDataService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IExtraPaymentData> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((extraPaymentData: HttpResponse<ExtraPaymentData>) => {
          if (extraPaymentData.body) {
            return of(extraPaymentData.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new ExtraPaymentData());
  }
}

export const extraPaymentDataRoute: Routes = [
  {
    path: '',
    component: ExtraPaymentDataComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ExtraPaymentData',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ExtraPaymentDataDetailComponent,
    resolve: {
      extraPaymentData: ExtraPaymentDataResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ExtraPaymentData',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ExtraPaymentDataUpdateComponent,
    resolve: {
      extraPaymentData: ExtraPaymentDataResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ExtraPaymentData',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ExtraPaymentDataUpdateComponent,
    resolve: {
      extraPaymentData: ExtraPaymentDataResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ExtraPaymentData',
    },
    canActivate: [UserRouteAccessService],
  },
];
