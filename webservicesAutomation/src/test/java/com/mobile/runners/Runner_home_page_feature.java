package com.mobile.runners;

import java.net.MalformedURLException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.cucumber.parallel.baseSteps.steps.NewBaseClass;
import com.cucumber.parallel.extent.ExtentManager;
import com.cucumber.parallel.extent.ExtentTestManager;

import cucumber.api.CucumberOptions;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.testng.TestNGCucumberRunner;


@CucumberOptions(
		features="src/test/resources/features/homepage.feature",
		glue="com.cucumber.parallel.homepage.steps"	
				//glue={"com.cucumber.parallel.mobile.runners"}
		)
public class Runner_home_page_feature extends NewBaseClass {
	
	private Scenario scenario;
	private String scenarioName;
	//private Object int i;
	int i=0;
	@Test
	public void runCuke()
	{
		new TestNGCucumberRunner(getClass()).runCukes();
		
	}
	
	@Before	
	public void before(Scenario scenario) throws Exception
	{
		//MyScreenRecorder.startRecording("navigationTest");
		i=i+1;
		this.scenario=scenario;
		String scenarioName=scenario.getName();
		System.out.println("SCENARIO NAME FOR TEST IS " +scenarioName );
		ExtentTestManager.startTest("Scenario No : "+i+" " + scenario.getName());
		ExtentTestManager.getTest().log(Status.INFO, "Scenario started is :-" + scenario.getName());
		//setUpFramework();
		
	}
	
	@After
	public void after(Scenario scenario) throws Exception
	{
		if(scenario.isFailed())
		{
			ExtentTestManager.logFail("Scenario failed");
			ExtentTestManager.addScreenShotOnFailure();
		}
		
		else
		{
			ExtentTestManager.logPass("Scenario passed");
			
		}
		ExtentManager.getReporter().flush();
		//MyScreenRecorder.stopRecording();
		quitAppiumDriver();
	}
	@AfterClass
	public void quitDriver()
	{
		getDriver().quit();
	}

}
