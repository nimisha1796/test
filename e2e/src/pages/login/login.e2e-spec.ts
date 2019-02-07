import { LoginPage } from './login.po';
import { protractor, browser } from 'protractor';
import { ArticlePage } from '../news/article.po';
import { SearchPage } from '../search/search.po';


describe('Login page', () => {
    let page: LoginPage;
    let article = new ArticlePage();
    let search = new SearchPage();
    const EC = protractor.ExpectedConditions;

    beforeEach(() => {
        page = new LoginPage();
        page.navigateToLoginPage();
    });
    it('should be able to login as analyst', () => {
        page.sendEmailForLogin().sendKeys('n@gmail.com');
        page.sendPasswordForLogin().sendKeys('111');
        page.getLoginButton().click();
        browser.wait(EC.visibilityOf(article.getTitle()));
        browser.sleep(2000);
        expect(browser.driver.getCurrentUrl()).toContain('/article');
      
    }); 


    it('should be able to login as admin' , () => {
        page.sendEmailForLogin().sendKeys('admin@gmail.com');
        page.sendPasswordForLogin().sendKeys('1111');
        page.getLoginButton().click();
        expect(browser.driver.getCurrentUrl()).toContain('/search');
        
    }); 
    it('should be able to get the errorMessage', () => {
        page.sendEmailForLogin().sendKeys('useru.');
        page.sendPasswordForLogin().sendKeys('3');
        page.getLoginButton().click();        
        expect(page.getErrorMessage().getText()).toEqual("Incorrect Email or Password");
    });   
   
});