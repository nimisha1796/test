import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
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
export class FavoriteService {

  constructor(private http: HttpClient) { }

  getUserFavArticle(email): Observable<any> {
    let articleFavUrl: string = "/search_news/signup/favorite/";
    return this.http.get(articleFavUrl + email, httpOptions);
  }

  deleteUserFavArticle(json): Observable<any> {
    let articleFavUrlDelete: string = "/search_news/signup/remove";
    return this.http.post(articleFavUrlDelete,json, httpOptions);
  }
}
