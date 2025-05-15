import type {IFilters, IId, TId} from '@/api/default/types';
import type { IUser } from '@/api/user/types';
import type { IComment } from '@/api/comment/types';
import type { ITech } from '@/api/tech/types';
import type { ITag } from '@/api/tag/types';

export interface IPost extends IId {
  title: string,
  content: string,
  language: string,
  author: IUser,
  comments: IComment[],
  techs: ITech[],
  tags: ITag[],
  createdAt: string,
}

export interface IPostCreate {
  title: string,
  content: string,
  language: string,
  techs: TId[],
  tags: TId[],
}

export interface IPostFilters extends IFilters {
  techs?: TId[],
  tags?: TId[],
  titleEntry?: string,
  author?: TId,
}
