
export type TId = string;

export interface IId {
  id: TId,
}

export interface IPagination<T> {
  items: T[],
  total: number,
  size: number,
  page: number,
}

export interface IFilters {
  [key: string]: string | string[] | number | undefined;
}
