package main.util;

import java.util.regex.Matcher;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import main.pages.common;

/**
 * Provided for Identifying the locators on bases of Dynamic web elements identification, so there is no need to hardcode of Locators.
 */
public class Locators extends common {

	/**
	 * A static method to read the locator values from page wise locator property files
	 * @param key as an defined in the Locator property files, path of the locator
	 * @return By
	 */
	public static By getBy(String key, String path){
		By by = null;
		//reading the locator values from locator property files
		//Splitting it to identify given key
		String locators[] = PropertyReader.getProperty(key, path).split("~");
		String locatorType = locators[0];
		String locator = locators[1];
		
		switch(locatorType.toLowerCase()){
		case "xpath":
			by = By.xpath(locator);
			break;
		case "id":
			by = By.id(locator);
			break;
		case "name":
			by = By.name(locator);
			break;
		case "class":
			by = By.className(locator);
			break;
		case "tagname":
			by = By.tagName(locator);
			break;
		case "css":
			by = By.cssSelector(locator);
			break;
		case "linktext":
			by = By.linkText(locator);
			break;
		case "partiallinktext":
			by = By.partialLinkText(locator);
			break;
		default:
			log_Handler.log.error("Invalid locator");
		}
		return by;	
	}
	
	/**
	 * A static method to read the locator values from page wise locator property files
	 * @param key as an defined in the Locator property files, path of the locator, any data which is dynamic and necessary to consider while identifying the webelements.
	 * @return By
	 */
	public static By getBy(String key, String path, String userdata){
		By by = null;
		//reading the locator values from locator property files
		//Splitting it to identify given key
		String locators[] = PropertyReader.getProperty(key, path).split("~");
		String locatorType = locators[0];
		String locator = locators[1];
		if(locator.contains("userdata"))
		{
			locator = locator.replaceAll("userdata", Matcher.quoteReplacement(userdata));
		}
		switch(locatorType.toLowerCase()){
		case "xpath":
			by = By.xpath(locator);
			break;
		case "id":
			by = By.id(locator);
			break;
		case "name":
			by = By.name(locator);
			break;
		case "class":
			by = By.className(locator);
			break;
		case "tagname":
			by = By.tagName(locator);
			break;
		case "css":
			by = By.cssSelector(locator);
			break;
		case "linktext":
			by = By.linkText(locator);
			break;
		case "partiallinktext":
			by = By.partialLinkText(locator);
			break;
		default:
			log_Handler.log.error("Invalid locator");
		}
		return by;	
	}
	
	/**
	 * A static method to read the locator values on basis of span text
	 * @param input text as span 
	 * @return By
	 */
	public static By byXpathSpanText(String text){
		return By.xpath("//span[text()='"+ text +"']");
	}
	
	/**
	 * A static method to read the locator values on basis of span text contains
	 * @param input text as span on basis of partial check 
	 * @return By
	 */
	public static By byXpathSpanTextContains(String text){
		return By.xpath("//span[contains(text(),'"+ text +"')]");
	}
	
	/**
	 * A static method to read the locator values on basis of partial link text
	 * @param input text as span on basis of partial link text 
	 * @return By
	 */
	public static By byPartialLinkText(String text){
		return By.partialLinkText(text);
	}
}
