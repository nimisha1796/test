import { browser, by, element, promise, ElementFinder, ElementArrayFinder } from 'protractor';

export class ArticlePage{
    getTitle(){
        return element(by.css('h2'));
    }
}