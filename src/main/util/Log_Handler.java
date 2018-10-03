package main.util;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.PropertyConfigurator;


/**
 * Provided for log4j log generation during test execution.
 * So a flexibility to create logs backup.
 */
public class Log_Handler{

	FileAppender fileAppender = new FileAppender();
	public Logger log = Logger.getLogger(Log_Handler.class.getName());
	String logfileLocation;
	
	/**
	 * @Description Constructor, to loading the log4j config properties.
	 */
	public Log_Handler() {
		
		String log4JPropLocation = FrameworkConstants.LOG4JPROPERTYFILELOC;
		PropertyConfigurator.configure(log4JPropLocation);
		log.info("Loading the log4j.properties file");
		logfileLocation = FrameworkConstants.LOGFILELOC;
	}
	
	
	/**
	 * @Description To delete the log file After a test case execution. (This method should be used after the Test end phase once a backup of the logfile has been taken.
	 * so it will have make sure that the logger file will have only current test log data. otherwise log file will be lost)
	 * To put at starting phase, It doesn't work.
	 * @return void
	 */
	 public void deleteLogFile(){
			
			try {
				//Creating a file instance
				File logfile = new File(logfileLocation);
				//Forming the system command
				String cmd = "cmd /C del " + logfile;
				//Executing the command using Command prompt.
				Runtime.getRuntime().exec(cmd);
				log.info("Log file deleted succesfully");
			} 
			catch (IOException e) {
				log.warn("IOException occured "+ e.getMessage()); 
				e.printStackTrace();
			}
	 }

		/**
		 * @Description To create a log file after deletion or at time when it is needed/ 
		 * But this is not needed is we are not using deleteLogFile method as log4J appender will automatically create the log file as defined in its config file.
		 * @return void
		 */
	 public void createLogFile(){
			try {
				//Creating a file instance
				File logfile = new File(logfileLocation);
				logfile.createNewFile();
				//Forming the system command
				log.info("Log file created succesfully");
			} 
			catch (IOException e) {
				log.warn("IOException occured "+ e.getMessage()); 
				e.printStackTrace();
			}
	 }

	/**
	 * @Description To copy the log file to the Log_backup folder with Test case name after each test case execution.
	 * @param testCaseName
	 * @return int, 1 for success & 0 for failure.
	 */
	 public int backupLogWriterFile(String testCaseName) {
		int result = 1; 
		String fileBackupLoc = FrameworkConstants.LOGFILEBACKUPLOC;

    	//Location of files, and file instantiation
		try {
			//Creating a file instance
			File logfile = new File(logfileLocation);
			File logfileBackupLoc = new File(fileBackupLoc);
			if (!logfileBackupLoc.exists())
			{
				logfileBackupLoc.mkdir();
			}
			File logBakupfile =new File(fileBackupLoc+"\\"+testCaseName+".log");
			//Forming the command string
			String cmd = "cmd /C copy /Y " + logfile + " " + logBakupfile + "\"";
			//Executing the command string using Command prompt.
			Runtime.getRuntime().exec(cmd);
			log.info("Log file backup created succesfully at :"+logBakupfile);
		} catch (IOException e) {
			log.warn("Log file was not able to copy hence a manual action is required "+ e.getMessage());
			result = 0;
			e.printStackTrace();
		}
		return result;
	 }

	
	/**
	 * @Description To write a content into the log file (Will write the content as log.info).
	 * @param contentToWrite
	 * @return int, 1 for success & 0 for failure.
	 */

	public int writeToLogFile(String contentToWrite) {
		int result = 1;
		//Using logging technique to write the content into same log file, The level is using to write is "INFO".
		log.warn(contentToWrite);
		return result;
	}
	
	/**
	 * @Description To write a content into the log file (As on need basis with some customized details).
	 * @param Lon level to write, contentToWrite
	 * @return int, 1 for success & 0 for failure.
	 */
	public int writeToLogFile(String level, String contentToWrite) {
	
		//2014-03-06 18:30:13,765 [INFO ] _testFrmwrk.Log_Handler.deleteLogWriterFile(Log_Handler.java:72)  - Log file deleted successfully.
		int result = 1;
		//get current date time
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		Date date = new Date();
		String systemDate = dateFormat.format(date);
		//Getting the class name
		String className = this.getClass().getName();
		
		try{
    		//Forming the date string 
			String data = systemDate+" ["+level+" ] "+className+"  - "+contentToWrite;
			System.out.println(data);
			//Create the file instance.
    		File file = new File(logfileLocation);
    		//if file doesn't exists, then create it
    		if(!file.exists()){
    			file.createNewFile();
    		}
    		//true = append file
			FileWriter fileWritter = new FileWriter(file,true);
	        BufferedWriter bufferWritter = new BufferedWriter(fileWritter);    
	        bufferWritter.write(data);
	        bufferWritter.newLine();
	        bufferWritter.close();
	        log.info("Log file updated wih defined level & contentToWrite");
	        
    	}catch(IOException e){
    		log.warn("Log file updation can't happen due to exception "+ e.getMessage());
    		e.printStackTrace();
    	}
		return result;
    }
	
	
	
	/**
	 * @Description For Internal use, To create a backup of test case wise execution logs.
	 * @param testCaseName
	 * @return void
	 */
	public void log4jTestExeBackup(String testCaseName) {
		//Assigning the logger path.	
		String logFileLoc = FrameworkConstants.LOGFILEBACKUPLOC +"\\"+testCaseName+".log";
		fileAppender.setFile(logFileLoc);
		//Assigning the pattern layout for logging contents
		fileAppender.setLayout(new PatternLayout("%d [%-5p] %l %x - %m%n"));
		fileAppender.setThreshold(Level.INFO);
		fileAppender.setAppend(false);
		fileAppender.activateOptions(); 
		//Adding appender dynamically.
		Logger.getRootLogger().addAppender(fileAppender);
		log.info("A log file as backup log holder of Test case :"+testCaseName+", at starting created at :"+logFileLoc);
	}
	
	
	/**
	 * @Description For Internal use, To place log data in correct log file (Not working as of now, Need to improve)
	 * @param testCaseName
	 * @return void
	 */
	public void backUpLogTestCaseWise(String testCaseName) {
		//Assigning the logger path.	
		String logFileLoc =  FrameworkConstants.LOGFILEBACKUPLOC +"\\"+testCaseName+".log";
		fileAppender.setFile(logFileLoc);
		//Assigning the pattern layout for logging contents
		fileAppender.setLayout(new PatternLayout("%d [%-5p] %l %x - %m%n"));
		fileAppender.setThreshold(Level.INFO);
		fileAppender.setAppend(true);
		fileAppender.activateOptions(); 
		//Adding appender dynamically.
		Logger.getRootLogger().addAppender(fileAppender);
		log.info("A log file as backup log holder of Test case :"+testCaseName+", at starting created at :"+logFileLoc);
	}
}
		
