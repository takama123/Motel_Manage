import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IConvenient } from 'app/shared/model/convenient.model';

type EntityResponseType = HttpResponse<IConvenient>;
type EntityArrayResponseType = HttpResponse<IConvenient[]>;

@Injectable({ providedIn: 'root' })
export class ConvenientService {
  public resourceUrl = SERVER_API_URL + 'api/convenients';

  constructor(protected http: HttpClient) {}

  create(convenient: IConvenient): Observable<EntityResponseType> {
    return this.http.post<IConvenient>(this.resourceUrl, convenient, { observe: 'response' });
  }

  update(convenient: IConvenient): Observable<EntityResponseType> {
    return this.http.put<IConvenient>(this.resourceUrl, convenient, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IConvenient>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IConvenient[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
