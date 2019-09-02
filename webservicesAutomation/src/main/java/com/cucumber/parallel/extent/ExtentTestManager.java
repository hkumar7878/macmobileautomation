package com.cucumber.parallel.extent;

import java.io.IOException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;


public class ExtentTestManager {
	
	public static ThreadLocal<ExtentTest> testReport= new ThreadLocal<ExtentTest>();
	
	static ExtentReports extent= ExtentManager.getReporter();
	
	
	public static synchronized ExtentTest getTest()
	{
		return testReport.get();
	}
	
	public static void logInfo(String msg)
	{
		testReport.get().info(msg);
	}
	public static void logInfo(Markup markup)
	{
		testReport.get().info(markup);
	}
	public static void logPass(String msg)
	{
		testReport.get().pass(msg);
	}
	
	public static void logFail(String msg)
	{
		testReport.get().fail(msg);
	}
	
	public static void logInfo1(Object[][] arrayResult)
	{
		Markup m=MarkupHelper.createTable((String[][]) arrayResult);

		testReport.get().log(Status.INFO, m);
	}
	
	public static synchronized boolean addScreenShotOnFailure() throws IOException
	{
		ExtentManager.captureScreenshot();
		testReport.get().fail("<b>" + "<font color=" + "red>" + "Screenshot of failure" + "</font>" + "</b>",
				MediaEntityBuilder.createScreenCaptureFromPath(ExtentManager.screenshotName).build());
		String failureLog="SCENARIO FAILED";
		Markup m=MarkupHelper.createLabel(failureLog, ExtentColor.RED);
		testReport.get().log(Status.FAIL, m);
		return true;
					
	}
	
	public static synchronized ExtentTest startTest(String testName)
	{
		return startTest(testName,testName);
	}
	
	public static synchronized ExtentTest startTest(String testName,String desc)
	{
		ExtentTest test=extent.createTest(testName,desc);
		testReport.set(test);
		return test;
	}

}
