import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IBiography, Biography } from 'app/shared/model/biography.model';
import { BiographyService } from './biography.service';
import { BiographyComponent } from './biography.component';
import { BiographyDetailComponent } from './biography-detail.component';
import { BiographyUpdateComponent } from './biography-update.component';

@Injectable({ providedIn: 'root' })
export class BiographyResolve implements Resolve<IBiography> {
  constructor(private service: BiographyService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IBiography> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((biography: HttpResponse<Biography>) => {
          if (biography.body) {
            return of(biography.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Biography());
  }
}

export const biographyRoute: Routes = [
  {
    path: '',
    component: BiographyComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Biographies',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: BiographyDetailComponent,
    resolve: {
      biography: BiographyResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Biographies',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: BiographyUpdateComponent,
    resolve: {
      biography: BiographyResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Biographies',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: BiographyUpdateComponent,
    resolve: {
      biography: BiographyResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Biographies',
    },
    canActivate: [UserRouteAccessService],
  },
];
