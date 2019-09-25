import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IMotel } from 'app/shared/model/motel.model';

type EntityResponseType = HttpResponse<IMotel>;
type EntityArrayResponseType = HttpResponse<IMotel[]>;

@Injectable({ providedIn: 'root' })
export class MotelService {
  public resourceUrl = SERVER_API_URL + 'api/motels';

  constructor(protected http: HttpClient) {}

  create(motel: IMotel): Observable<EntityResponseType> {
    return this.http.post<IMotel>(this.resourceUrl, motel, { observe: 'response' });
  }

  update(motel: IMotel): Observable<EntityResponseType> {
    return this.http.put<IMotel>(this.resourceUrl, motel, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMotel>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMotel[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
