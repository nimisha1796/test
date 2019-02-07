import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { SignupService } from '../signup.service';
import { LanguageService } from '../language.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  signup = this.fb.group({
    name: ['', Validators.required],
    email: ['', Validators.required],
    password: ['', Validators.compose([Validators.required, Validators.minLength(2), Validators.maxLength(8)])],
    language: this.fb.group({
      id: [''],
      language: ['']
    })

  });
  error: boolean = false;
  success: boolean = false;
  message: any;
  errorMessage: any;
  successMessage: any;
  condition = false;
  list: any;
  constructor(private fb: FormBuilder, private service: SignupService, private languageService: LanguageService) { }
  ngOnInit() {
    this.languageService.getLanguages().subscribe(
      data => {
        this.list = data;
        console.log(data);
      })
  }
  saveUser() {
    console.log("data");
    console.log(this.signup.value)
    this.service.saveUserOnSignup(this.signup.value).subscribe(
      data => {
        this.message = data;
        console.log(data);

      },
      error => {
        this.error = true;
        this.errorMessage = error;
        console.log(this.error);

      },
    )
  }
  onSubmit() {
    console.log
    console.warn(this.signup.value);
  }

}
