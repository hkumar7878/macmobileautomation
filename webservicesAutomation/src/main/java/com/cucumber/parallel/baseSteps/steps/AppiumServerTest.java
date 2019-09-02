package com.cucumber.parallel.baseSteps.steps;

import java.io.File;
import java.text.SimpleDateFormat;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class AppiumServerTest {
	
	static String NodePath="C:/Program Files/nodejs/node.exe";
	static String appiumPath="C:/Users/308758/AppData/Local/Programs/Appium/resources/app/node_modules/appium/build/lib/main.js";
	static AppiumDriverLocalService service;
	static SimpleDateFormat df= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	
	
	@BeforeTest
	public void startServer()
	{
		service=AppiumDriverLocalService.buildService(new AppiumServiceBuilder().
				usingDriverExecutable(new File(NodePath)).withAppiumJS(new File(appiumPath))
				.withIPAddress("127.0.0.1").usingAnyFreePort());
		System.out.println("Starting the appium server ");
		service.start();
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void start()
	{
		
	}
	
	@AfterTest
	public void afterTEst()
	{
		service.stop();
	}
	
	
	

}
