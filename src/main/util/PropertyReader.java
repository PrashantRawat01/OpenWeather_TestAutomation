package main.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;


/**
 * Provided for reading the properties from locator property files
 */
public class PropertyReader {
	

	/**
	 * @Description A static method to read variable from Property files.
	 * @param unique key & File name to read
	 * @return String
	 */
	public static String getVariable(String key, String filename){
		return getProperty(key, filename);
	}
	
	/**
	 * @Description A static method to read values from Property files.
	 * @param unique key & File name to read
	 * @return String
	 */
	public static String getProperty(String key, String path){
		String value=null;
		try {
			//Creating a file instance
			File file = new File(path);
			//Used input streem
			FileInputStream fileInput = new FileInputStream(file);
			//Used instance of properties
			Properties properties = new Properties();
			//Load the property file as per givem input
			properties.load(fileInput);
			//File input close for property
			fileInput.close();
			value = properties.getProperty(key);
			} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return value;
	}

}
