export class User{
    id?:number;
    username: string;
    email: string;
    password: string;
    rol_id?: number;
    token?: string;
    
  constructor(username: string, email: string, password: string) {
    this.username = username;
    this.email = email;
    this.password = password;
  }
}
