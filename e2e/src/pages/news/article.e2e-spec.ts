import { protractor, browser } from 'protractor';
import { LoginPage } from '../login/login.po';
import { ArticlePage } from './article.po';

describe('Search Article Page',()=>{
    let article = new ArticlePage();
    let login= new LoginPage();
    const EC=protractor.ExpectedConditions;

    beforeEach(()=>{        
        login.navigateToLoginPage();
    });    
/* 
    it('Should Get the Title Expected',()=>{
        login.sendEmailForLogin().sendKeys('n@gmail.com');
        login.sendPasswordForLogin().sendKeys('111');
        login.getLoginButton().click();
        browser.wait(EC.visibilityOf(article.getTitle()));        
        expect(article.getTitle().getText()).toEqual('Search Article Page');
        
    }); */
})