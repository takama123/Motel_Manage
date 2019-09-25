import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IBillDetails, BillDetails } from 'app/shared/model/bill-details.model';
import { BillDetailsService } from './bill-details.service';
import { BillDetailsComponent } from './bill-details.component';
import { BillDetailsDetailComponent } from './bill-details-detail.component';
import { BillDetailsUpdateComponent } from './bill-details-update.component';

@Injectable({ providedIn: 'root' })
export class BillDetailsResolve implements Resolve<IBillDetails> {
  constructor(private service: BillDetailsService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IBillDetails> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((billDetails: HttpResponse<BillDetails>) => {
          if (billDetails.body) {
            return of(billDetails.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new BillDetails());
  }
}

export const billDetailsRoute: Routes = [
  {
    path: '',
    component: BillDetailsComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'BillDetails',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: BillDetailsDetailComponent,
    resolve: {
      billDetails: BillDetailsResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'BillDetails',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: BillDetailsUpdateComponent,
    resolve: {
      billDetails: BillDetailsResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'BillDetails',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: BillDetailsUpdateComponent,
    resolve: {
      billDetails: BillDetailsResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'BillDetails',
    },
    canActivate: [UserRouteAccessService],
  },
];
