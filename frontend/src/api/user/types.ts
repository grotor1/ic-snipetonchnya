import type { IId } from '@/api/default/types';

export enum ERole {
  USER = 'USER',
  ADMIN = 'ADMIN',
}

export interface IUserCreate {
  login: string,
  email: string,
  password: string,
  fullName: string,
  ghLogin?: string,
}

export interface IUser extends IId {
  login: string,
  email: string,
  fullName: string,
  ghLogin: string,
  role: ERole,
  blocked: boolean,
}

export interface IUserCredentials {
  password: string,
  login: string,
}

export interface ITokenCouple {
  refreshToken: string,
  accessToken: string,
}

export interface ITokenRefresh {
  refreshToken: string;
}
