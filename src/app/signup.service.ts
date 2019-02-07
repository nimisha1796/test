import { Injectable } from '@angular/core';
import { HttpHeaders } from '@angular/common/http';
import { HttpClient } from '@angular/common//http';
import { Observable } from 'rxjs';
const httpOptions = {
  headers: new HttpHeaders(
    {
      'Content-Type': 'application/json',
    }
  )
};

@Injectable({
  providedIn: 'root'
})
export class SignupService {

  url: string = "/search_news/signup/save";
  constructor(private http: HttpClient) { }
  saveUserOnSignup(user): Observable<any> {
    console.log("data")
    return this.http.post<any>(this.url, user, httpOptions);
  }

}
