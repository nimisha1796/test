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
export class ArticleService {

  constructor(private http: HttpClient) { }

  apiKey: string = "935c162538714cdabfd057aadb88ec4e";

  getNewsArticle(languageCode): Observable<any> {
    let articleUrl: string = "https://newsapi.org/v2/top-headlines?language=" + languageCode + "&apiKey=" + this.apiKey;
    return this.http.get(articleUrl);

  }
  getFavNewsArticle(json): Observable<any> {
    let articleFavUrl: string = "/search_news/signup/search";
    return this.http.post(articleFavUrl, json, httpOptions);
  }
  searchArticleOnKeyword(keyword): Observable<any> {
    let articleSearchUrl: string = "https://newsapi.org/v2/everything?q=" + keyword + "&apiKey=" + this.apiKey;
    return this.http.get(articleSearchUrl);

  }
}