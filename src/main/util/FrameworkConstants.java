package main.util;

import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * A class to provide the path of necessary repository and paths.
 * Making these as final to have them set only once.
 * 
 */
public class FrameworkConstants {
	public static final String DRIVER_TYPE = "Chrome";
	public static final String CHROME_DRIVEREXE_PATH = ".\\src\\test\\resources\\Drivers\\chromedriver_32.exe";
	public static final String GECKO_32_DRIVEREXE_PATH = ".\\src\\test\\resources\\Drivers\\geckodriver_32.exe";
	public static final String GECKO_64_DRIVEREXE_PATH = ".\\src\\test\\resources\\Drivers\\geckodriver_64.exe";
	public static final String IE_32_DRIVEREXE_PATH = ".\\src\\test\\resources\\Drivers\\IEDriverServer_32.exe";
	public static final String IE_64_DRIVEREXE_PATH = ".\\src\\test\\resources\\Drivers\\IEDriverServer_64.exe";
	public static final String APP_URL = "https://openweathermap.org";
	public static final String WORKING_DIR = "..";
	public static final String RESOURCES_FOLDER = ".\\src\\test\\resources\\";
	public static final String RESOURCES_LOCATOR = ".\\src\\test\\resources\\Locators\\";
	public static final String CHROME_EXTENSION_FOLDER = "/chrome-extensions";
	
	//Logger Files & its backup files, ScreenShot location:
	public static final String LOG4JPROPERTYFILELOC = ".\\src\\test\\resources\\Drivers\\log4j.properties";
	public static final String LOGFILELOC = ".\\logs\\Logger.log";
	public static final String LOGFILEBACKUPLOC = ".\\logs\\LogBackup";
	//public static final String DATAFILEBACKUP = ".\\logs\\DataFileBackup";
	public static final String ERRORSCREENSHOTLOC = ".\\logs\\ScreenShots";

	
}
