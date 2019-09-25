import { IBiography } from 'app/shared/model/biography.model';
import { IContract } from 'app/shared/model/contract.model';
import { Gender } from 'app/shared/model/enumerations/gender.model';
import { Nation } from 'app/shared/model/enumerations/nation.model';

export interface ICustomer {
  id?: number;
  firstName?: string;
  lastName?: string;
  gender?: Gender;
  birthday?: string;
  extraName?: string;
  originAddress?: string;
  nation?: Nation;
  religion?: string;
  nationality?: string;
  indentity?: string;
  regularlyAdress?: string;
  address?: string;
  academicLevel?: string;
  qualification?: string;
  foreignLevel?: string;
  job?: string;
  email?: string;
  passsword?: string;
  phone?: string;
  status?: boolean;
  biographies?: IBiography[];
  contracts?: IContract[];
}

export class Customer implements ICustomer {
  constructor(
    public id?: number,
    public firstName?: string,
    public lastName?: string,
    public gender?: Gender,
    public birthday?: string,
    public extraName?: string,
    public originAddress?: string,
    public nation?: Nation,
    public religion?: string,
    public nationality?: string,
    public indentity?: string,
    public regularlyAdress?: string,
    public address?: string,
    public academicLevel?: string,
    public qualification?: string,
    public foreignLevel?: string,
    public job?: string,
    public email?: string,
    public passsword?: string,
    public phone?: string,
    public status?: boolean,
    public biographies?: IBiography[],
    public contracts?: IContract[]
  ) {
    this.status = this.status || false;
  }
}
