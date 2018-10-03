/**
 * 
 */
package main.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import main.util.Locators;

import main.util.FrameworkConstants;

/**
 * @author rawatp1
 *
 */
public class HomePage extends common{

	String locationFileName = "HomePage_Chrome.properties"; 
	
	/**
	 * Constructor to identify the browser type property as locators could be different as per a web browser.
	 */
	public HomePage(){
		
        switch(FrameworkConstants.DRIVER_TYPE.toUpperCase()) 
        { 
            case "CHROME": 
            	locationFileName = "HomePage_Chrome.properties"; 
                break; 
            case "EE": 
            	locationFileName = "HomePage_FF.properties"; 
                break; 
            default: 
                break; 
        } 
    } 
	
	//----------------------------Locators Area----------------------------//
	String repoPath = FrameworkConstants.RESOURCES_LOCATOR + locationFileName; 
	
	By HeaderBar = Locators.getBy("headerBar", repoPath);
	By BrandNameImage = Locators.getBy("brandNameImage", repoPath);
	By HeaderNavigatonBar = Locators.getBy("headerNavigatonBar", repoPath);
	By MainSlideShow = Locators.getBy("mainSlideShow", repoPath);
	By WedgitMainGlobalMap = Locators.getBy("wedgitMainGlobalMap", repoPath);
	By Footer = Locators.getBy("footer", repoPath);
	
	By SearchBox = Locators.getBy("searchBox", repoPath);
	By SearchButton = Locators.getBy("searchButton", repoPath);
	By SearchResultPane = Locators.getBy("searchResultPane", repoPath);
	public By SearchSpecificResultToUserData (String userData){
		return Locators.getBy("searchSpecificResult", repoPath, userData);
	}

	
	//-----------------------------Method Area-----------------------------//
	
	/**
	 * A methods to verify Home page elements.
	 * @return boolean
	 */
	public boolean verifyHomePageElements(){
		try {
			testStatus = true; 
			//All the webelements on home page are validated
			testStatus = appDriver.findElement(HeaderBar).isDisplayed() &&
			appDriver.findElement(BrandNameImage).isDisplayed() &&
			appDriver.findElement(HeaderNavigatonBar).isDisplayed() &&
			appDriver.findElement(MainSlideShow).isDisplayed() &&
			appDriver.findElement(WedgitMainGlobalMap).isDisplayed() &&
			appDriver.findElement(Footer).isDisplayed();
			
			log_Handler.log.info("Sucessfully validated Home page");
		} catch (Exception e) {
			testStatus = false; 
			log_Handler.log.error("There is some exception during validating of Home page");
		}
		return testStatus;
		
	}
	
	/**
	 * A methods to enter search text in search box of home page.
	 * @param searchText
	 * @return boolean
	 */
	public boolean searchTextInSearchBox(String searchText){
		try {
			testStatus = true; 
			WebElement searhBoxElement = appDriver.findElement(SearchBox);
			searhBoxElement.clear();
			searhBoxElement.sendKeys(searchText);
			appDriver.findElement(SearchButton).click();
			log_Handler.log.info("Sucessfully searched text in search box");
		} catch (Exception e) {
			testStatus = false; 
			log_Handler.log.error("There is some exception during searching a text in search box");
		}
		return testStatus;
		
	}
	
	/**
	 * A methods to verify if the search result is displayed post given a search.
	 * @param searchText
	 * @return boolean
	 */
	public boolean verifySearchResultDislayed(String searchText){
		try {
			testStatus = true; 
			//finding elements for searchResultPaneDisplay
			WebElement searchResultPaneDisplay = appDriver.findElement(SearchResultPane);
			//updating staus as per result displayed for the element
			testStatus = appDriver.findElement(SearchSpecificResultToUserData(searchText)).isDisplayed();		
			log_Handler.log.info("Search Result displayed succesfully");
		} catch (Exception e) {
			testStatus = false; 
			log_Handler.log.error("There is some exception during searching a text in search box");
		}
		return testStatus;
		
	}
	
	/**
	 * A methods to verify that the search result should not be displayed if a invalid city name given to search.
	 * @return boolean
	 */
	public boolean verifySearchResultNotDislayed(){
		try {
			testStatus = true; 
			//finding elements for searchResultPaneDisplay
			WebElement searchResultPaneDisplay = appDriver.findElement(SearchResultPane);
			//updating staus as per result displayed for the element
			String searchResultText = searchResultPaneDisplay.getText();
			if(searchResultText.equals("Not found"))
			log_Handler.log.info("Search Result not displayed as expected");
			testStatus = true;
		} catch (Exception e) {
			testStatus = false; 
			log_Handler.log.error("There is some exception during searching a text in search box");
		}
		return testStatus;
		
	}
		
}
