import { IMotel } from 'app/shared/model/motel.model';
import { IRoom } from 'app/shared/model/room.model';

export interface IFile {
  id?: number;
  originName?: string;
  name?: string;
  path?: string;
  motel?: IMotel;
  room?: IRoom;
}

export class File implements IFile {
  constructor(
    public id?: number,
    public originName?: string,
    public name?: string,
    public path?: string,
    public motel?: IMotel,
    public room?: IRoom
  ) {}
}
