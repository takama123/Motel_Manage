import { IMotel } from 'app/shared/model/motel.model';
import { IRoom } from 'app/shared/model/room.model';

export interface IConvenient {
  id?: number;
  title?: string;
  decription?: string;
  motel?: IMotel;
  room?: IRoom;
}

export class Convenient implements IConvenient {
  constructor(public id?: number, public title?: string, public decription?: string, public motel?: IMotel, public room?: IRoom) {}
}
