import { IBillDetails } from 'app/shared/model/bill-details.model';

export interface IExtraPaymentData {
  id?: number;
  cost?: number;
  decription?: string;
  billDetails?: IBillDetails;
}

export class ExtraPaymentData implements IExtraPaymentData {
  constructor(public id?: number, public cost?: number, public decription?: string, public billDetails?: IBillDetails) {}
}
