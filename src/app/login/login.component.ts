import { Component, OnInit } from '@angular/core';
import { LoginService } from '../login.service';
import { Router } from '@angular/router';
import { FormBuilder, Validators } from '@angular/forms';
import { AuthService } from '../auth.service';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  errorMessage:any;
  constructor(private fb: FormBuilder,private loginService: LoginService, private service: AuthService, private router: Router) { }

  ngOnInit() {
  }

  login = this.fb.group({
    email: ['',Validators.required],
    password: ['',Validators.required]
  });
  error: any;
  loggedIn=false;

  authenticate() {

    this.loginService.loginUser(this.login.value).subscribe(
      data => {
        console.log("authenticate User")
        console.log(data)
        if (data.status && data.user.role.id==2) {
              this.loggedIn=true;
              this.service.setUserRole(data.user.role.role);      
              this.service.languageCode=data.user.language.code;
              this.service.languageid=data.user.language.id;
              this.service.email= data.user.email;
              this.service.setUserLogin(this.loggedIn);
              console.log(data.user.language.code);
              this.router.navigate(['/article']);
        }
         if (data.user.role.id==1) {
           
      this.router.navigate(['/search']);
        }
      },
      
      error => {
        this.error = true;
        this.errorMessage = error;
        console.log(this.error);

      },);

this.login.reset();
  }
  signupUser(){
    this.router.navigate(['/signup']);
  }
}
