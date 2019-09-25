import { IMotel } from 'app/shared/model/motel.model';
import { IRoom } from 'app/shared/model/room.model';

export interface IServices {
  id?: number;
  title?: string;
  price?: number;
  decription?: string;
  motel?: IMotel;
  room?: IRoom;
}

export class Services implements IServices {
  constructor(
    public id?: number,
    public title?: string,
    public price?: number,
    public decription?: string,
    public motel?: IMotel,
    public room?: IRoom
  ) {}
}
