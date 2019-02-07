import { Component, OnInit } from '@angular/core';
import { FavoriteService } from '../favorite.service';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-favorite',
  templateUrl: './favorite.component.html',
  styleUrls: ['./favorite.component.css']
})
export class FavoriteComponent implements OnInit {

  email: any;
  userList: any;
  loggedIn = false;
  error: any;

  constructor(private favoriteService: FavoriteService, private service: AuthService) { }

  ngOnInit() {
    this.favoriteService.getUserFavArticle(this.service.email).subscribe(
      data => {
        console.log("User")
        console.log(data)
        this.userList = data.article;
        console.log(this.userList)
      },
      error => {
        this.error = error;
      });
  }
  deleteFavoriteArticle(article) {
    this.email = this.service.getUserEmail();
    let json = JSON.stringify({
      email: this.email,
      article: [
        article
      ]
    }
    )
    this.favoriteService.deleteUserFavArticle(json).subscribe(data => {
      this.favoriteService.getUserFavArticle(this.service.email).subscribe(
        data => {
          console.log("User")
          console.log(data)
          this.userList = data.article;
          console.log(this.userList)
        },
        error => {
          this.error = error;
        });
    })
  }
}
