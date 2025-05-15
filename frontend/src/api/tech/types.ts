import type { IId } from '@/api/default/types';

export interface ITech extends IId {
  label: string;
  createdAt: string;
}

export interface ITechCreate {
  label: string;
}
