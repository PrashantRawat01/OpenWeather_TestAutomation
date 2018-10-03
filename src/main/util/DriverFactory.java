package main.util;
import java.util.HashMap;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.beust.jcommander.Parameter;

import main.pages.common;

public class DriverFactory {

	public static WebDriver appDriver;
	private static String currentFolderPath;

	/**
	 * A static method to initialize the webdriver as per given browser type.
	 * @return void
	 */
	public static void initializeDriver(String browserType) {
			switch(browserType.toUpperCase()) {
			case "CHROME":
				//Creating instance of chrome options
				ChromeOptions chromeoptions = null;
				System.setProperty("webdriver.chrome.driver", FrameworkConstants.CHROME_DRIVEREXE_PATH);			
				HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
				chromeoptions = new ChromeOptions();
				//Forming the appDriver - Chrome
				appDriver = new ChromeDriver(chromeoptions);
				appDriver.manage().window().maximize();
				break; 
			case "FF":
				System.setProperty("webdriver.gecko.driver", common.is64BitSystem() ? FrameworkConstants.GECKO_64_DRIVEREXE_PATH : FrameworkConstants.GECKO_32_DRIVEREXE_PATH);
				FirefoxOptions ffoptions = new FirefoxOptions();
				//Forming the appDriver - Firefox
				appDriver = new FirefoxDriver(ffoptions);
				appDriver.manage().window().maximize();
				break;
			default:
				break;
			}
		}

	/**
	 * A static method to stop the webdriver.
	 * @return void
	 */
	public static void stopDriver() {
		//Stoping instace of webdriver
		appDriver.close();
		}

	/**
	 * A static method to quit the webdriver.
	 * @return void
	 */
	public static void quitDriver() {	
		//Quitting all instace of webdriver
		appDriver.quit();
		}

	/**
	 * A static method to get the current folder path.
	 * @return void
	 */
	public static String getCurrentFolderPath(){
		return currentFolderPath;
	}

}
