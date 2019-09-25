import { IFile } from 'app/shared/model/file.model';
import { IServices } from 'app/shared/model/services.model';
import { IConvenient } from 'app/shared/model/convenient.model';
import { IContract } from 'app/shared/model/contract.model';
import { IMotel } from 'app/shared/model/motel.model';
import { RoomStatus } from 'app/shared/model/enumerations/room-status.model';

export interface IRoom {
  id?: number;
  title?: string;
  price?: number;
  status?: RoomStatus;
  acreage?: number;
  decription?: string;
  images?: IFile[];
  services?: IServices[];
  convenients?: IConvenient[];
  contracts?: IContract[];
  motel?: IMotel;
}

export class Room implements IRoom {
  constructor(
    public id?: number,
    public title?: string,
    public price?: number,
    public status?: RoomStatus,
    public acreage?: number,
    public decription?: string,
    public images?: IFile[],
    public services?: IServices[],
    public convenients?: IConvenient[],
    public contracts?: IContract[],
    public motel?: IMotel
  ) {}
}
