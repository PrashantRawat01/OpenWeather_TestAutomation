
package main.pages;
import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.List;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.imageio.ImageIO;
import org.testng.asserts.SoftAssert;
import main.util.DriverFactory;
import main.util.FrameworkConstants;
import main.util.Log_Handler;

/**
 * Provided for initializing all the common methods across OpenWeather project.
 */
public class common extends DriverFactory {

	//Instantiating the testStaus, a boolean parameter which can define the final status of Test script.
	public static boolean testStatus= true;
	//Instantiating the TestAssert for testng test scripts execution. An execution will be continued if there is any failure. This will be captured at last of script.
	public static SoftAssert softAssert;
	//Instantiating the LogHandler class, to generate all the logs per test case wise
	public static Log_Handler log_Handler;
	//Instantiating a counter for unique screenshot number
	public static int testScreenShotSeriel;
	
	
	/**
	 * A common method to Start of Test environment.
	 * @param testCaseName, browserType
	 * @return void
	 */
	public void startTestEnvironment(String testCaseName, String browserType){
		try {
			
			testScreenShotSeriel = 0;
			
			softAssert = new SoftAssert();
			log_Handler = new Log_Handler();
			//A new logger file with the name of test case formed here
			log_Handler.log4jTestExeBackup(testCaseName);
			//initializing webdriver with the Browsertype
			initializeDriver(browserType);
			log_Handler.log.info("AppDriver initialized");
			//Launch the application URL in the webdriver
			appDriver.get(FrameworkConstants.APP_URL);
            log_Handler.log.info("Application is launched successfully");
		} catch (Exception e) {
			log_Handler.log.warn("There is an exception during startTestEnvironment :"+ e.getMessage());
			testStatus = false;
		}
	}
	
	/**
	 * Common methods to close Test environment.
	 * @param testCaseName
	 * @return void
	 */
	public void closeTestEnvironment(String testCaseName) {
		if (testStatus) {
			log_Handler.log.info("TestCase status is Pass");
		}else {
			if(appDriver!= null){
			log_Handler.log.error("TestCase status is fail");
			//taking screenshot with the name of given test case & serial number.
			takeScreenShot(testCaseName+"_"+testScreenShotSeriel++);
			}
		}
		stopDriver();
		quitDriver();
		}
		
	/**
	 * Common methods to Take screenshot per failure during test execution.
	 * @param nameOfScreenShot
	 * @return void
	 */
	public void takeScreenShot(String nameOfScreenShot){
		
		File screenShotFolder = new File(FrameworkConstants.ERRORSCREENSHOTLOC);
		try {
			//Checking if he Screenshot folder exist.
			if (screenShotFolder.exists()){
			}else {
				//Making a directory
				screenShotFolder.mkdir();
			}
			//Forming file name for screenshot
			String fileName = screenShotFolder+"\\"+ nameOfScreenShot +".jpg";
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	   		Rectangle screenRectangle = new Rectangle(screenSize);
	   		Robot robot;
	   		//Using Robot class for screenshot. Didn't use the selenium screenshot as sometime the instance of wedriver is lost and then the screenshot woudn't work.
			robot = new Robot();
			BufferedImage image = robot.createScreenCapture(screenRectangle);
			ImageIO.write(image, "png", new File(fileName));
			log_Handler.log.info("Capturing the screen shot and saving at: "+fileName);
		 } catch (AWTException e) {
			log_Handler.log.warn("AWTException Exception occurred during screenshot processing :" + e.getMessage());
		    e.printStackTrace();
		} catch (IOException e) {
			log_Handler.log.warn("IOException Exception occurred during screenshot processing :" + e.getMessage());
	    	e.printStackTrace();
		}
   	}
	
	/**
	 * Common methods to identify the systems' bit version on bases of Sselenium Driver version passes to script.
	 * @return void
	 */
	public static boolean is64BitSystem(){
		boolean is64bit = false;
		if (System.getProperty("os.name").contains("Windows")) {
		    is64bit = (System.getenv("ProgramFiles(x86)") != null);
		} else {
		    is64bit = (System.getProperty("os.arch").indexOf("64") != -1);
		}
		return is64bit;
	}

	/**
	 * Common methods to identify if the link available on webpage is broken.
	 * @param url of the link
	 * @return string
	 */
	public static String isLinkBroken(URL url) throws Exception
	 {
 		String response = "";
 		//HttrURLConnection lib is used to open http connection
 		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
 		try
 		{
 		    connection.connect();
 		    response = connection.getResponseMessage();	
 		    //Post checking http url response closing connection
 		    connection.disconnect();
 		    return response;
 		}
 		catch(Exception exp)
 		{
 			return exp.getMessage();
 		}  				
 
	}
	
	
	
}
