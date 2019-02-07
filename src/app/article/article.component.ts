import { Component, OnInit } from '@angular/core';
import { ArticleService } from '../article.service';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-article',
  templateUrl: './article.component.html',
  styleUrls: ['./article.component.css']
})
export class ArticleComponent implements OnInit {
  constructor(private articleService: ArticleService, private service: AuthService) { }
  isSaved = false;
  currentArticleTitle: any = '';
  key: any;
  languageCode: any;
  articleList: any;
  articles: any;
  title: any;
  email: any;
  userArticle: any;
  userArticle1:any;
   ngOnInit() {
    this.isSaved = false;
    this.languageCode = this.service.languageCode;
    console.log("Language code is : ");
    console.log(this.languageCode);
    this.articleService.getNewsArticle(this.languageCode).subscribe(data => {
      console.log(data)
      this.articleList = data.articles;

    })


  }

  addUserFavourites(title, description) {
    this.isSaved = true;
   this.currentArticleTitle=title
    console.log("User Email:");
    console.log(this.service.email);
    this.userArticle = JSON.stringify({
      "email": this.service.email,
      "article": [
        {
          "title": title,
          "description": description,
          "language": {
            "id": this.service.languageid
          }
        }
      ]
    });
    console.log("Fav article is : ");
    console.log(this.userArticle);
    this.articleService.getFavNewsArticle(this.userArticle).subscribe(data => {
      console.log("Added to Favourites");
      this.userArticle=data;
      console.log(data)

    });
  }
  searchArticle(keyword) {
    this.articleService.searchArticleOnKeyword(keyword).subscribe(data => {
      console.log(data)
      this.articleList = data.articles;
      console.log()
    })
  }

}
