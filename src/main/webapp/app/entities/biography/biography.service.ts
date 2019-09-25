import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IBiography } from 'app/shared/model/biography.model';

type EntityResponseType = HttpResponse<IBiography>;
type EntityArrayResponseType = HttpResponse<IBiography[]>;

@Injectable({ providedIn: 'root' })
export class BiographyService {
  public resourceUrl = SERVER_API_URL + 'api/biographies';

  constructor(protected http: HttpClient) {}

  create(biography: IBiography): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(biography);
    return this.http
      .post<IBiography>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(biography: IBiography): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(biography);
    return this.http
      .put<IBiography>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IBiography>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IBiography[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(biography: IBiography): IBiography {
    const copy: IBiography = Object.assign({}, biography, {
      fromDate: biography.fromDate && biography.fromDate.isValid() ? biography.fromDate.format(DATE_FORMAT) : undefined,
      toDate: biography.toDate && biography.toDate.isValid() ? biography.toDate.format(DATE_FORMAT) : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.fromDate = res.body.fromDate ? moment(res.body.fromDate) : undefined;
      res.body.toDate = res.body.toDate ? moment(res.body.toDate) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((biography: IBiography) => {
        biography.fromDate = biography.fromDate ? moment(biography.fromDate) : undefined;
        biography.toDate = biography.toDate ? moment(biography.toDate) : undefined;
      });
    }
    return res;
  }
}
