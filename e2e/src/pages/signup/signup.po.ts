import { browser, by, element } from 'protractor';

export class SignupPage {

    navigateToSignupPage() {
        return browser.get('/signup');
    }
    sendEmailForSignup() {
        return element(by.id('signupemail'));

    }
    sendNameForSignup() {
        return element(by.id('signupname'));

    }
    sendPasswordForSignup() {
        return element(by.id('signuppassword'));

    }
    getbuttonForSignup() {
        return element(by.css('btn btn-primary'));

    }
    sendLanguageForSignup() {
    return element(by.id('signuplanguage'));

}

}