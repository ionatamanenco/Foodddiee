export interface User {
  id?: string;
  email?: string;
  firstname?: string;
  lastname?: string;
  password: string;
  role: Role;
  userRestaurantId: string;
  token?: string;
}

export enum Role {
  USER,
  ADMIN,
}
