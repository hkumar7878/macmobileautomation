package com.nlapp.api;

import cucumber.api.java.Before;
import com.aventstack.extentreports.Status;
import com.cucumber.parallel.baseSteps.steps.NewBaseClass;
import com.cucumber.parallel.extent.ExtentManager;
import com.cucumber.parallel.extent.ExtentTestManager;
import cucumber.api.Scenario;
import cucumber.api.java.After;


public class CucumberHooks extends NewBaseClass{
// Idea behind declaring cucumber hooks is there that in order to execute hooks, these should be
	// present in the same package where the step definitions are defined otherwise these would not 
	// be executed. Once, these are defined in same package, just glue in the runner file.
	
	//private scenario;
	private String scenarioName;
	int i=0;
	private Scenario scenario;
	
	
	@Before
	public void beforeEachScenario(Scenario scenario) throws Exception
	{
		i=i+1;
		this.scenario=scenario;
		scenarioName=scenario.getName();
		System.out.println("SCENARIO NAME FOR TEST IS " +scenarioName );
		ExtentTestManager.startTest("Device Name : +deviceName +" + "Scenario No : "+i+" " + scenario.getName());
		System.out.println("Scenario name is--->" + "Scenario No : "+i+" " + scenario.getName());
		ExtentTestManager.getTest().log(Status.INFO, "Scenario started is :-" + scenario.getName());
		//setUpFramework();
		
	}
	
	@After
	public void afterEachScenario(Scenario scenario) throws Exception
	{
		if(scenario.isFailed()){
			ExtentTestManager.logFail("Scenario failed");
			ExtentTestManager.addScreenShotOnFailure();
		}	
		else
			ExtentTestManager.logPass("Scenario passed");
			
		ExtentManager.getReporter().flush();
		quitAppiumDriver();
	}
}
