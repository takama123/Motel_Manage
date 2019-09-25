import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IExtraPaymentData } from 'app/shared/model/extra-payment-data.model';

type EntityResponseType = HttpResponse<IExtraPaymentData>;
type EntityArrayResponseType = HttpResponse<IExtraPaymentData[]>;

@Injectable({ providedIn: 'root' })
export class ExtraPaymentDataService {
  public resourceUrl = SERVER_API_URL + 'api/extra-payment-data';

  constructor(protected http: HttpClient) {}

  create(extraPaymentData: IExtraPaymentData): Observable<EntityResponseType> {
    return this.http.post<IExtraPaymentData>(this.resourceUrl, extraPaymentData, { observe: 'response' });
  }

  update(extraPaymentData: IExtraPaymentData): Observable<EntityResponseType> {
    return this.http.put<IExtraPaymentData>(this.resourceUrl, extraPaymentData, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IExtraPaymentData>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IExtraPaymentData[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
