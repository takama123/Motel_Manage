import { IFile } from 'app/shared/model/file.model';
import { IRoom } from 'app/shared/model/room.model';
import { IConvenient } from 'app/shared/model/convenient.model';
import { IServices } from 'app/shared/model/services.model';

export interface IMotel {
  id?: number;
  title?: string;
  address?: string;
  phone?: string;
  decription?: string;
  electricityPrice?: number;
  waterPrice?: number;
  images?: IFile[];
  rooms?: IRoom[];
  convenients?: IConvenient[];
  services?: IServices[];
}

export class Motel implements IMotel {
  constructor(
    public id?: number,
    public title?: string,
    public address?: string,
    public phone?: string,
    public decription?: string,
    public electricityPrice?: number,
    public waterPrice?: number,
    public images?: IFile[],
    public rooms?: IRoom[],
    public convenients?: IConvenient[],
    public services?: IServices[]
  ) {}
}
