package main.testScripts;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import main.pages.common;
/**
 * Description: To test the Home page is launched properly and all the necessary components displayed on the home page.
 * @category Functional Test
 * @author Prashant Rawat
 */
public class Test5 extends common {
    
    private static WebDriver driver = null;

   
    String testCaseName = this.getClass().getSimpleName();
	//Map<Integer, Map<String, String>> excelInputParamDataMap = new LinkedHashMap<Integer, Map<String, String>>();

    
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
	 * A @Test methods to Verify that all the links available on Home page are valid links
	 * @return void
	 */
    @Test(description="Checking count for all the links available on Home page ")
    public void LinlsOnHomePage(){  
    	String url = "";
        //HttpURLConnection httpConnection = null;
        //int respCode = 200;
        //Creating a list of links available on page
        List<WebElement> links = appDriver.findElements(By.tagName("a"));
        testStatus = true;
                
        if(!(links.size() >= 0)) {
        	log_Handler.log.error("These are no link on the page ");
        	}
        else 
        	log_Handler.log.info("These are " + links.size() + "links on the page");
      //Used to report all the failures.
        softAssert.assertTrue(testStatus, "Validation failed for links of the page.!");
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