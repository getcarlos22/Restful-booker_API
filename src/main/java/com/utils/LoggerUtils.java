package com.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class LoggerUtils {

	private Logger logger;
	private String logFilePath;
	private String fileName;

	public LoggerUtils(Class<?> ClassName) {

		Properties props = new Properties();
		try {
			props.load(
					new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/" + "log4j.properties"));
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

		PropertyConfigurator.configure(props);

		logger = Logger.getLogger(ClassName);

		String currDate = new SimpleDateFormat("dd MMMM yyyy").format(Calendar.getInstance().getTime());
		new File(System.getProperty("user.dir") + "/logs/Log - " + currDate).mkdirs();

		logFilePath = System.getProperty("user.dir") + "/logs/Log - " + currDate;

		String currTime = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
		fileName = "Test Run - " + currTime.replaceAll(":", "-") + ".log";

	}

	public void error(String message) {

		Calendar cal = Calendar.getInstance();
		Date currentTime = cal.getTime();

		Logger.getRootLogger().setLevel(Level.ERROR);

		logger.error("ERROR" + " " + currentTime + ": " + message);
		writeToLogFile("ERROR" + " " + currentTime + ": " + message);

	}

	public void info(String message) {

		Calendar cal = Calendar.getInstance();
		Date currentTime = cal.getTime();

		Logger.getRootLogger().setLevel(Level.INFO);

		logger.info("INFO" + " " + currentTime + ": " + message);
		writeToLogFile("INFO" + " " + currentTime + ": " + message);

	}

	public void debug(String message) {

		Calendar cal = Calendar.getInstance();
		Date currentTime = cal.getTime();

		Logger.getRootLogger().setLevel(Level.DEBUG);

		logger.debug("DEBUG" + " " + currentTime + ": " + message);
		writeToLogFile("DEBUG" + " " + currentTime + ": " + message);

	}

	public void warn(String message) {

		Calendar cal = Calendar.getInstance();
		Date currentTime = cal.getTime();

		Logger.getRootLogger().setLevel(Level.WARN);

		logger.warn("WARN" + " " + currentTime + ": " + message);
		writeToLogFile("WARN" + " " + currentTime + ": " + message);

	}

	public void pass(String message) {

		Calendar cal = Calendar.getInstance();
		Date currentTime = cal.getTime();

		Logger.getRootLogger().setLevel(Level.INFO);

		logger.info("PASS" + " " + currentTime + ": " + message);
		writeToLogFile("PASS" + " " + currentTime + ": " + message);

	}

	/*
	 *  This method explicitly throw TestFailure exception on test failure
	 */
	public void fail(String message) {

		Calendar cal = Calendar.getInstance();
		Date currentTime = cal.getTime();

		Logger.getRootLogger().setLevel(Level.ERROR);

		logger.error("FAIL" + " " + currentTime + ": " + message);
		writeToLogFile("FAIL" + " " + currentTime + ": " + message);
		throw new TestFailureException("Test step failed..");

	}

	public void writeToLogFile(String content) {

		FileWriter fw = null;
		BufferedWriter bw = null;

		try {
			fw = new FileWriter(logFilePath + "/" + fileName, true);
			bw = new BufferedWriter(fw);
			bw.newLine();
			bw.write(content);
			bw.flush();
			fw.close();
			bw.close();
		} catch (IOException e) {

			Logger.getRootLogger().setLevel(Level.ERROR);

			logger.error("ERROR" + " " + "Can't write to Log File, IOException occurred!!!!!!!!!!!!!!!!! \n");
			e.printStackTrace();

		}

	}

}