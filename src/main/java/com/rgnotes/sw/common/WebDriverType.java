package com.rgnotes.seleniumworkshop.common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;

import com.rgnotes.seleniumworkshop.common.WebDriverSetup;

import java.util.Arrays;
import java.util.HashMap;

public enum WebDriverType implements WebDriverSetup {

	FIREFOX {
        public DesiredCapabilities getDesiredCapabilities() {
            DesiredCapabilities capabilities = DesiredCapabilities.firefox();
            return capabilities;
        }

        public WebDriver getWebDriverObject(DesiredCapabilities capabilities) {
        	System.setProperty("webdriver.gecko.driver", "webdrivers\\geckodriver.exe");
            return new FirefoxDriver(capabilities);
        }
    },
	CHROME {
        public DesiredCapabilities getDesiredCapabilities() {
            DesiredCapabilities capabilities = DesiredCapabilities.chrome();
            capabilities.setCapability("chrome.switches", Arrays.asList("--no-default-browser-check"));
            HashMap<String, String> chromePreferences = new HashMap<String, String>();
            chromePreferences.put("profile.password_manager_enabled", "false");
            capabilities.setCapability("chrome.prefs", chromePreferences);
            return capabilities;
        }

        public WebDriver getWebDriverObject(DesiredCapabilities capabilities) {
        	System.setProperty("webdriver.chrome.driver", "webdrivers\\chromedriver.exe");
            return new ChromeDriver(capabilities);
        }            
    },
	IE {
        public DesiredCapabilities getDesiredCapabilities() {
            DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();capabilities.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);capabilities.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, true);
            capabilities.setCapability("requireWindowFocus", true);
            return capabilities;
        }

        public WebDriver getWebDriverObject(DesiredCapabilities capabilities) {
        	System.setProperty("webdriver.ie.driver", "webdrivers\\iedriver.exe");
            return new InternetExplorerDriver(capabilities);
        }
    };
}
