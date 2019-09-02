package com.cucumber.grid.app.pages;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.cucumber.parallel.baseSteps.steps.NewBaseClass;
import com.cucumber.parallel.extent.ExtentTestManager;
import com.nlapp.utility.GenericUtility_NLApp;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class Page_Home_NLAPP extends NewBaseClass{
	
	public static AppiumDriver<WebElement> driver;
	public static boolean isEventSuccessful;
	public Page_Home_NLAPP(AppiumDriver<WebElement> driver)
	{
		this.driver=(AppiumDriver<WebElement>) driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@AndroidFindBy(xpath="//android.widget.FrameLayout[@content-desc='Products']/android.widget.ImageView")
	public AndroidElement tabProduct;
	
	public Page_Products_NLAPP clickTab_Product()
	{
		try {
			isEventSuccessful=GenericUtility_NLApp.clickElement(tabProduct);
			
			Assert.assertTrue(isEventSuccessful);
			ExtentTestManager.logInfo("Product tab clicked successfully");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ExtentTestManager.logFail("Click button could not be clicked");
			return null;
		}
		return new Page_Products_NLAPP(driver);
	
	}
	
}


