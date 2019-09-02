package com.mobile.runners;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import com.cucumber.parallel.baseSteps.steps.NewBaseClass;


import cucumber.api.CucumberOptions;

import cucumber.api.testng.TestNGCucumberRunner;


@CucumberOptions(
		features="src/test/resources/features/Scenario1_MiddleWare_Parent_Category_Verification.feature",
		glue="com.nlapp.api"	
				
		)
public class Scenario1_MiddleWare_Parent_Category_Verification extends NewBaseClass {
	
	
	@Test
	public void runCuke()
	{
		new TestNGCucumberRunner(getClass()).runCukes();
		
	}	
	

	@AfterClass
	public void quitDriver()
	{
		getDriver().quit();
	}

}
