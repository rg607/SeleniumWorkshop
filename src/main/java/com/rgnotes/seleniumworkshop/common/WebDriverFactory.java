package com.rgnotes.seleniumworkshop.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.rgnotes.seleniumworkshop.listeners.ScreenshotListener;
import org.testng.annotations.Listeners;

@Listeners(ScreenshotListener.class)
public class WebDriverFactory {

	private static List<WebDriverThread> webDriverThreadPool = Collections.synchronizedList(new ArrayList<WebDriverThread>());
	private static ThreadLocal<WebDriverThread> driverThread;
	
	@BeforeSuite
    public static void instantiateDriverObject() {
        driverThread = new ThreadLocal<WebDriverThread>() {
            @Override
            protected WebDriverThread initialValue() {
                WebDriverThread webDriverThread = new WebDriverThread();
                return webDriverThread;
            }
        };
    }
	
	public static WebDriver getDriver() throws Exception {
		return driverThread.get().getDriver();
	}
	
	@AfterMethod
    public static void clearCookies() throws Exception {
        getDriver().manage().deleteAllCookies();
    }
	
	@AfterMethod
    public static void quitDriver() throws Exception {
        driverThread.get().quitDriver();
    }
	
	 @AfterSuite
	 public static void closeDriverObjects() {
		 for (WebDriverThread webDriverThread : webDriverThreadPool) {
			 System.out.println("quitDriver");
	            webDriverThread.quitDriver();
	     }
	 }	
}
