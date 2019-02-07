import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor() {}
  status:boolean
  loggedIn: boolean
  role: string;
  languageCode: any;
  email: string;
  languageid:any;

 

  setUserLogin(loggedIn: boolean) {
    console.log("Inside auth service login()");
    this.loggedIn = loggedIn;
  }
  getUserLogin() {
    return this.loggedIn;
  }
  getUserRole() {
    return this.role;
  }

  setUserRole(role: string) {
    this.role = role;
  }

  getUserLanguageCode() {
    return this.languageCode;
  }

  setUserLanguageCode(languageCode: string) {
    this.languageCode = languageCode;
  }

  getUserEmail() {
    return this.email;
  }

  setUserEmail(email: string) {
    this.email = email;
  }
  getStatus() {
    return this.status
  }
  setStatus(status: boolean) {
    this.status = status
  }
}
