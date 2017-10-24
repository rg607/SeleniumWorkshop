package com.rgnotes.seleniumworkshop.samples;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.testng.annotations.Test;

import com.rgnotes.seleniumworkshop.common.WebDriverFactory;

public class BasicTestWD extends WebDriverFactory {
	
    private void googleExampleThatSearchesFor(final String searchString) throws Exception {

        WebDriver driver = WebDriverFactory.getDriver();
        driver.get("http://www.google.com");

        WebElement searchField = driver.findElement(By.name("q"));

        searchField.clear();
        searchField.sendKeys(searchString);

        System.out.println("Page title is: " + driver.getTitle());

        searchField.submit();

        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driverObject) {
                return driverObject.getTitle().toLowerCase().startsWith(searchString.toLowerCase());
            }
        });

        System.out.println("Page title is: " + driver.getTitle());       
        //System.out.println("Is this change getting committed to GitHub");
    }

    @Test
    public void googleCheeseExample() throws Exception {
        googleExampleThatSearchesFor("Cheese!");
    }
}
