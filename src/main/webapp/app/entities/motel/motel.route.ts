import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IMotel, Motel } from 'app/shared/model/motel.model';
import { MotelService } from './motel.service';
import { MotelComponent } from './motel.component';
import { MotelDetailComponent } from './motel-detail.component';
import { MotelUpdateComponent } from './motel-update.component';

@Injectable({ providedIn: 'root' })
export class MotelResolve implements Resolve<IMotel> {
  constructor(private service: MotelService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IMotel> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((motel: HttpResponse<Motel>) => {
          if (motel.body) {
            return of(motel.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Motel());
  }
}

export const motelRoute: Routes = [
  {
    path: '',
    component: MotelComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Motels',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: MotelDetailComponent,
    resolve: {
      motel: MotelResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Motels',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: MotelUpdateComponent,
    resolve: {
      motel: MotelResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Motels',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: MotelUpdateComponent,
    resolve: {
      motel: MotelResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Motels',
    },
    canActivate: [UserRouteAccessService],
  },
];
