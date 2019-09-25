import { Moment } from 'moment';
import { IExtraPaymentData } from 'app/shared/model/extra-payment-data.model';
import { IContract } from 'app/shared/model/contract.model';

export interface IBillDetails {
  id?: number;
  startDate?: Moment;
  endDate?: Moment;
  oldKwh?: number;
  oldWater?: number;
  newKwh?: number;
  newWater?: number;
  roomPrice?: number;
  electricityPrice?: number;
  waterPrice?: number;
  extraPaymentDatas?: IExtraPaymentData[];
  contract?: IContract;
}

export class BillDetails implements IBillDetails {
  constructor(
    public id?: number,
    public startDate?: Moment,
    public endDate?: Moment,
    public oldKwh?: number,
    public oldWater?: number,
    public newKwh?: number,
    public newWater?: number,
    public roomPrice?: number,
    public electricityPrice?: number,
    public waterPrice?: number,
    public extraPaymentDatas?: IExtraPaymentData[],
    public contract?: IContract
  ) {}
}
