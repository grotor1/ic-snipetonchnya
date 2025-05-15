import type { IId } from '@/api/default/types';
import type { IUser } from '@/api/user/types';

export interface IComment extends IId {
  author: IUser;
  content: string;
  createdAt: string;
}

export interface ICommentCreate {
  content: string;
}
