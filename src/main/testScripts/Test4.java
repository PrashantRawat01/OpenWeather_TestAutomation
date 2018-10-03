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
public class Test4 extends common {
    
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
    @Test(description="Checking all the links available on Home page are valid links")
    public void validLinlsOnPage(){  
    	String url = "";
        HttpURLConnection httpConnection = null;
        int respCode = 200;
        //Creating a list of links available on page
        List<WebElement> links = appDriver.findElements(By.tagName("a"));
        Iterator<WebElement> it = links.iterator();
        List<String> brokenlinks = new ArrayList<String>();
        //Iterating through the list of links
        while(it.hasNext()){  
            url = it.next().getAttribute("href");
            log_Handler.log.info(url);
        //checking is a link is null/empty
            if(url == null || url.isEmpty()){
            	log_Handler.log.info("URL is either not configured for anchor tag or it is empty");
                continue;
            }
            //checking is a link is pointing to mail system
            if(url.contains("mailto")){
            	log_Handler.log.info("URL is not a valid link as its pointing a mail system");
                continue;
                }
            
            try {
            	//forming a http connection for identified links
                httpConnection = (HttpURLConnection)(new URL(url).openConnection());
                httpConnection.setRequestMethod("HEAD");
                httpConnection.connect();
                respCode = httpConnection.getResponseCode();
                
                if(respCode >= 400){
                	log_Handler.log.error(url+" is a broken link");
                	testStatus = false;
                	brokenlinks.add(url);
                }
                else{
                	log_Handler.log.info(url+" is a valid link");
                }
                httpConnection.disconnect();
                // For any exception, catching it here.
            } catch (MalformedURLException e) {
            	log_Handler.log.error("There is an MalformedURLException: " + e.getMessage());
            } catch (IOException e) {
            	log_Handler.log.error("There is an IOException: " + e.getMessage());
            } catch (Exception e) {
            	log_Handler.log.error("There is an exception: " + e.getMessage());
            }
            
        }
        
        if(brokenlinks.size() > 0) {
        	log_Handler.log.error("These are the broken links: "+ brokenlinks);
        	}
        else 
        	log_Handler.log.info("These are no broken links on the page");
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