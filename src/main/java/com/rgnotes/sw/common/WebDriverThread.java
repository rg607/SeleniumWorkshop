package com.rgnotes.sw.common;

import com.rgnotes.sw.common.WebDriverType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;

import static com.rgnotes.sw.common.WebDriverType.FIREFOX;
import static com.rgnotes.sw.common.WebDriverType.valueOf;

public class WebDriverThread {
	
	private WebDriver webdriver;
	private WebDriverType selectedWebDriverType;

	private final WebDriverType defaultDriverType = FIREFOX;
	private final String browser = System.getProperty("browser").toUpperCase();
	private final String operatingSystem = System.getProperty("os.name").toUpperCase();
    private final String systemArchitecture = System.getProperty("os.arch");
    
    public WebDriver getDriver() throws Exception {
    	
    	if (null == webdriver) {
    		selectedWebDriverType = finalizeWebDriverType();
            DesiredCapabilities desiredCapabilities = selectedWebDriverType.getDesiredCapabilities();
            instantializeWebDriver(desiredCapabilities);
        }
    	
    	return webdriver;
    }    
     
    public void quitDriver() {
        if (null != webdriver) {
        	System.out.println("quitting the browser");
            webdriver.quit();
            webdriver = null;
        }
    }
    
    private WebDriverType finalizeWebDriverType() {
        WebDriverType driverType = defaultDriverType;
        
        try {
            driverType = valueOf(browser);
        } catch (IllegalArgumentException ignored) {
            System.err.println("Unknown driver specified, defaulting to '" + driverType + "'...");
        } catch (NullPointerException ignored) {
            System.err.println("No driver specified, defaulting to '" + driverType + "'...");
        }
        
        return driverType;
    }
    
    private void instantializeWebDriver(DesiredCapabilities desiredCapabilities) throws MalformedURLException {
        System.out.println(" ");
        System.out.println("Current Operating System: " + operatingSystem);
        System.out.println("Current Architecture: " + systemArchitecture);
        System.out.println("Current Browser Selection: " + selectedWebDriverType);System.out.println(" ");
        webdriver = selectedWebDriverType.getWebDriverObject(desiredCapabilities);
    }
    
}
