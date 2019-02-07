import { TestBed, async } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { AppComponent } from './app.component';
import { SignupComponent } from './signup/signup.component';
import { LoginComponent } from './login/login.component';
import { ArticleComponent } from './article/article.component';
import { SearchComponent } from './search/search.component';
import { FavoriteComponent } from './favorite/favorite.component';
import { Routes } from '@angular/router';
import { HeaderComponent } from './header/header.component';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { APP_BASE_HREF } from '@angular/common';

fdescribe('AppComponent', () => {
  const routes: Routes = [
    { path: "signup", component: SignupComponent },
    { path: "", component: LoginComponent },
    { path: "article", component: ArticleComponent },
    { path: "search", component: SearchComponent },
    { path: "favorite", component: FavoriteComponent }
  
  
  ];
  beforeEach(async(() => {
    
    TestBed.configureTestingModule({
      declarations: [
        AppComponent,
        SignupComponent,
        LoginComponent,
        ArticleComponent,
        SearchComponent,
        FavoriteComponent,
        HeaderComponent
      ],
      imports: [
        BrowserModule,
        AppRoutingModule,
        FormsModule,
        ReactiveFormsModule,
        HttpClientModule,
      ],
      providers: [{provide: APP_BASE_HREF, useValue : '/' }],
    }).compileComponents();
  }));

  it('should create the app', () => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.debugElement.componentInstance;
    expect(app).toBeTruthy();
  });

  it(`should have as title 'newsSearchUI'`, () => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.debugElement.componentInstance;
    expect(app.title).toEqual('newsSearchUI');
  });

});
