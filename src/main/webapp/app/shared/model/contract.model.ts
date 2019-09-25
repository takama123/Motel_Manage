import { Moment } from 'moment';
import { IBillDetails } from 'app/shared/model/bill-details.model';
import { ICustomer } from 'app/shared/model/customer.model';
import { IRoom } from 'app/shared/model/room.model';

export interface IContract {
  id?: number;
  checkInDate?: Moment;
  checkOutDate?: Moment;
  decription?: string;
  paymentDatas?: IBillDetails[];
  customer?: ICustomer;
  room?: IRoom;
}

export class Contract implements IContract {
  constructor(
    public id?: number,
    public checkInDate?: Moment,
    public checkOutDate?: Moment,
    public decription?: string,
    public paymentDatas?: IBillDetails[],
    public customer?: ICustomer,
    public room?: IRoom
  ) {}
}
