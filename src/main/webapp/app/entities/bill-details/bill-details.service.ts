import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IBillDetails } from 'app/shared/model/bill-details.model';

type EntityResponseType = HttpResponse<IBillDetails>;
type EntityArrayResponseType = HttpResponse<IBillDetails[]>;

@Injectable({ providedIn: 'root' })
export class BillDetailsService {
  public resourceUrl = SERVER_API_URL + 'api/bill-details';

  constructor(protected http: HttpClient) {}

  create(billDetails: IBillDetails): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(billDetails);
    return this.http
      .post<IBillDetails>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(billDetails: IBillDetails): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(billDetails);
    return this.http
      .put<IBillDetails>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IBillDetails>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IBillDetails[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(billDetails: IBillDetails): IBillDetails {
    const copy: IBillDetails = Object.assign({}, billDetails, {
      startDate: billDetails.startDate && billDetails.startDate.isValid() ? billDetails.startDate.format(DATE_FORMAT) : undefined,
      endDate: billDetails.endDate && billDetails.endDate.isValid() ? billDetails.endDate.format(DATE_FORMAT) : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.startDate = res.body.startDate ? moment(res.body.startDate) : undefined;
      res.body.endDate = res.body.endDate ? moment(res.body.endDate) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((billDetails: IBillDetails) => {
        billDetails.startDate = billDetails.startDate ? moment(billDetails.startDate) : undefined;
        billDetails.endDate = billDetails.endDate ? moment(billDetails.endDate) : undefined;
      });
    }
    return res;
  }
}
