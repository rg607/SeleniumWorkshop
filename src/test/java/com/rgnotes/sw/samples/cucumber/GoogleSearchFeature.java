package com.rgnotes.sw.samples.cucumber;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;

public class GoogleSearchFeature extends CucumberRunner{
	
	@Given("^I am on google page$")
	public void googlePage() throws Throwable {
		String title = driver.getTitle();
		Assert.assertEquals(title, "Google");
	}
	
	@When("^I type \"(.*?)\"$")
	public void searchText(String text) throws Throwable {
		driver.findElement(By.cssSelector("input[name='q']")).sendKeys(text);
	}
	
	@Then("^I click search button$")
	public void searchButton() throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver, 10);
	    wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[name='q']")));
	    
		driver.findElement(By.cssSelector("input[name='q']")).submit();
	}

	@Then("^I clear search textbox$")
	public void Clear() throws Throwable {
		
		WebDriverWait wait = new WebDriverWait(driver, 10);
	    wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[name='q']")));
	    
		driver.findElement(By.cssSelector("input[name='q']")).clear();
	}
}