import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LoginComponent } from './login.component';
import { AppComponent } from '../app.component';
import { SignupComponent } from '../signup/signup.component';
import { ArticleComponent } from '../article/article.component';
import { SearchComponent } from '../search/search.component';
import { FavoriteComponent } from '../favorite/favorite.component';
import { HeaderComponent } from '../header/header.component';
import { BrowserModule, By } from '@angular/platform-browser';
import { AppRoutingModule } from '../app-routing.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { APP_BASE_HREF } from '@angular/common';
import { LoginService } from '../login.service';
import { DebugElement } from '@angular/core';

fdescribe('LoginComponent', () => {
  let component: LoginComponent;
  let fixture: ComponentFixture<LoginComponent>;
  let de : DebugElement;
  let el : HTMLElement;

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
      providers: [{provide: APP_BASE_HREF, useValue : '/' }, LoginService],
    })
    .compileComponents().then(() =>{
      fixture = TestBed.createComponent(LoginComponent);
      component = fixture.componentInstance;
      de = fixture.debugElement.query(By.css('form'));
      el = de.nativeElement;

    });
  }));

  // beforeEach(() => {
  //   fixture = TestBed.createComponent(LoginComponent);
  //   component = fixture.componentInstance;
  //   fixture.detectChanges();
  // });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('form should valid when field is not empty',async(()=> {
    //accessing controls
    component.login.controls['email'].setValue('Rahulkamble01@gmail.com');
    component.login.controls['password'].setValue('Rahulkamble01');
    expect(component.login.valid).toBeTruthy();
  }));

  it('form should invalid when field is empty',async(()=> {
    //accessing controls
    component.login.controls['email'].setValue('');
    component.login.controls['password'].setValue('');
    expect(component.login.valid).toBeFalsy();
  }));

  it('should call the authenticate method', async(()=> {
    fixture.detectChanges();
    spyOn(component, 'authenticate');
    el =fixture.debugElement.query(By.css('button')).nativeElement;
    el.click();
    expect(component.authenticate).toHaveBeenCalledTimes(1);
  }));
  


});
