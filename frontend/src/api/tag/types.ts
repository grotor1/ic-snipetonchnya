import type { IId } from '@/api/default/types';

export interface ITag extends IId {
  label: string;
  createdAt: string;
}

export interface ITagCreate {
  label: string;
}
