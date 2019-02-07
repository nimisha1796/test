import { browser, by, element, protractor } from 'protractor';
import { SignupPage } from './signup.po';
import { LoginPage } from '../login/login.po';
describe('Signup Page', () => {
    let page: SignupPage;
    let login : LoginPage;
    const EC = protractor.ExpectedConditions;
    beforeEach(() => {
        page = new SignupPage();
        page.navigateToSignupPage();
    }); 
    it('should be able to signup as analyst', () => {
        page.sendEmailForSignup().sendKeys('n@gmail.com');
        page.sendNameForSignup().sendKeys('Nimisha');
        page.sendPasswordForSignup().sendKeys('111');
        page.sendLanguageForSignup().sendKeys('English');
        page.getbuttonForSignup().click();
        browser.sleep(2000)
        expect(browser.getCurrentUrl()).toContain('');
      });

      it('should be not able to signup as analyst if emailId already present', () => {
        page.sendEmailForSignup().sendKeys('n@gmail.com');
        page.sendNameForSignup().sendKeys('Nimisshhhaa');
        page.sendPasswordForSignup().sendKeys('1234');
        page.sendLanguageForSignup().sendKeys("english");
        page.getbuttonForSignup().click();
        browser.sleep(2000)
        expect(browser.driver.getCurrentUrl()).toContain('');
    });
});
