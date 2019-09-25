import { Moment } from 'moment';
import { ICustomer } from 'app/shared/model/customer.model';

export interface IBiography {
  id?: number;
  fromDate?: Moment;
  toDate?: Moment;
  address?: string;
  workingDecription?: string;
  customer?: ICustomer;
}

export class Biography implements IBiography {
  constructor(
    public id?: number,
    public fromDate?: Moment,
    public toDate?: Moment,
    public address?: string,
    public workingDecription?: string,
    public customer?: ICustomer
  ) {}
}
