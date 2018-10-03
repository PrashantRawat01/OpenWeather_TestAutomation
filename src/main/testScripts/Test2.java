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

public class Test2 extends common{
  
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
	 * A @Test methods to Verify the user is able to search using search box
	 * @param CityName as a search text
	 * @return void
	 */
	@Test(testName = "searchText", description= "Verify the user is able to search using search box", timeOut = 30000)
	@Parameters({ "Param1" })
	public void searchTextinSearchBox(String CityName){
		log_Handler.log.info("Inside searchTextinSearchBox");
		
		testStatus = hp.searchTextInSearchBox(CityName);
	    if(!testStatus){
	    	testStatus = false;
	    	log_Handler.log.error("VerifyApptitleMethod failed..!");
	    	//Taking screenshot in case of failure
			takeScreenShot(testCaseName+"_"+testScreenShotSeriel++);
	    	}
	    //Used assertion for a result.
	    softAssert.assertTrue(testStatus, "Sucessfully searched for Text!");
	  //Used to report all the failures.
	    softAssert.assertAll();
	}

	/**
	 * A @Test methods to Verify that the results are not displyed if user is given a invalid city name/arbitory data as a search
	 * @return void
	 */
	@Test(testName = "VerifySearchResultNotDisplayed", description= "Verify that the results are not displyed if user is given a invalid city name/arbitory data as a search", timeOut = 30000)
	public void verifySearhResultNotDisplayed(){
		log_Handler.log.info("Inside verifySearhResultNotDisplayed");
		
		testStatus = hp.verifySearchResultNotDislayed();
		if(!testStatus){
	    	testStatus = false;
	    	log_Handler.log.error("VerifyApptitleMethod failed..!");
	    	//Taking screenshot in case of failure
			takeScreenShot(testCaseName+"_"+testScreenShotSeriel++);
	    	}
	    //Used assertion for a result.
	    softAssert.assertTrue(testStatus, "Validation Passed for No result.!");
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
