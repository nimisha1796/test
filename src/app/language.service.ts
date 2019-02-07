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
export class LanguageService {

  url: string = "/search_news/signup/language";           
  constructor(private http: HttpClient) { }  
  getLanguages():Observable<any>{
    console.log("data")
    return this.http.get<any>(this.url);
  }
}