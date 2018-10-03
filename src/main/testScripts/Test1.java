package main.testScripts;
import java.util.LinkedHashMap;
import java.util.Map;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import main.pages.HomePage;
import main.pages.common;
/**
 * Description: To test the Home page is launched properly and all the necessary components displayed on the home page.
 * @category Functional Test
 * @author Prashant Rawat
 */

public class Test1 extends common{
  
	String testCaseName = this.getClass().getSimpleName();
	//Map<Integer, Map<String, String>> excelInputParamDataMap = new LinkedHashMap<Integer, Map<String, String>>();
	HomePage hp = new HomePage();

	
	/**
	 * A @BeforeTest method to initialize the driver & other necessary instances of logger and assert..
	 * @return void
	 */
	@BeforeClass(description="Intilize all the required dataset")
	@Parameters({ "BrowserType" })
	public void beforeTest(String browserType){
		startTestEnvironment(testCaseName, browserType);
		testStatus = true;
	}

	/**
	 * A @Test methods to verify the title of the navigated page and all the necessary elements are available on the page.
	 * @return void
	 */
	@Test(testName = "verifyTitleOfPage", description= "Verify the title of the navigated page"/*, timeOut = 30000*/)
	public void verifyAppTitle2(){
		log_Handler.log.info("Inside verifyTitleOfPage");
		String expectedTitle = "OpenWeatherMap";
	    String actualTitle = appDriver.getTitle();
	    //Validating the title text
	    if(!actualTitle.contains(expectedTitle)){
	    	testStatus = false;
	    	log_Handler.log.error("VerifyApptitleMethod failed..!");
	    	//In case of error, taking screenshot
			takeScreenShot(testCaseName+"_"+testScreenShotSeriel++);
	    	}
	    softAssert.assertTrue(testStatus, "Validation failed for Title of the page.!");
	    //Validating other elements on the home page
	    if(!hp.verifyHomePageElements()){
	    	testStatus = false;
	    	log_Handler.log.error("VerifyApptitleMethod failed..!");
	    	//Taking screenshot for a failure
			takeScreenShot(testCaseName+"_"+testScreenShotSeriel++);
	    	}
	    //Used assertion for a result.
	    softAssert.assertTrue(testStatus, "Validation failed for minimal page elements on the homepage.!");
	   //Used to report all the failures.
		softAssert.assertAll();
	}
	
	/**
	 * A @AfterTest method to close the driver..
	 * @return void
	 */
	@AfterClass(description="Ending test case")
	public void afterTest(){
		//Closing test environment
		closeTestEnvironment(testCaseName);
	}

}
