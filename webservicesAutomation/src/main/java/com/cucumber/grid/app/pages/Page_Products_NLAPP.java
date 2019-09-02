package com.cucumber.grid.app.pages;


import java.util.ArrayList;
import java.util.List;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.offset.PointOption;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.cucumber.parallel.baseSteps.steps.NewBaseClass;
import com.nlapp.utility.AppUtility_NLApp;

public class Page_Products_NLAPP extends NewBaseClass{

public static AppiumDriver<WebElement> driver;
boolean isEventSuccessful=false;
String productsTabTitle;
List<String> prt_Ctgry_Names;
List<String> prtCtrNames= new ArrayList<String>();

public Page_Products_NLAPP(AppiumDriver<WebElement> driver)
{
 this.driver=(AppiumDriver<WebElement>) driver;
 PageFactory.initElements(new AppiumFieldDecorator(driver), this);
}

@AndroidFindBy(id="nz.co.noelleeming.mynlapp.qat:id/title")
public AndroidElement productTitle;

/*@AndroidFindBy(id="nz.co.noelleeming.mynlapp.staging:id/title")
public AndroidElement productTitle;*/

@AndroidFindBy(className = "nz.co.noelleeming.mynlapp.qat:id/label​")
//@AndroidFindBys({@AndroidFindBy(id = "nz.co.noelleeming.mynlapp.qat:id/label​")})
public List<MobileElement> txt_ParentCategories;

//

/*@AndroidFindBy(className = "nz.co.noelleeming.mynlapp.staging:id/label​")
//@AndroidFindBys({@AndroidFindBy(id = "nz.co.noelleeming.mynlapp.qat:id/label​")})
public List<MobileElement> txt_ParentCategories;*/

@FindBys(@FindBy(id = "label"))
public List<MobileElement> txt_ParentCategories1;

@AndroidFindBy(xpath = "//*[@resource-id='nz.co.noelleeming.mynlapp.qat:id/label']")
//@AndroidFindBys({@AndroidFindBy(id = "nz.co.noelleeming.mynlapp.qat:id/label​")})
public List<MobileElement> txt_ParentCategories2;

public List<String> getParentCategoryNames1()
{
 try
 {
  prtCtrNames=AppUtility_NLApp.fetch_ViewGroup_Items_Android(txt_ParentCategories2);
  
 }
 catch(Exception e)
 {
  System.out.println("Exception occured" + e.getMessage());
 }
 return prtCtrNames;
}

public boolean verifyProductsTab()
{
 try
 {
	 productsTabTitle=productTitle.getText();
	 Assert.assertEquals(productsTabTitle, "Popular Categories");
	 isEventSuccessful=true;
 }
 
 catch(Exception e)
 {
	 System.out.println("Exception occured" + e.getMessage());
	 isEventSuccessful=false;
 }
 	return isEventSuccessful;
}

public List<String> getParentCategoryNames()
{
 try
 {
	 new TouchAction(driver).press(PointOption.point(550, 1000)).waitAction().moveTo(PointOption.point(650, 110)).release().perform();
	 //js.executeScript("mobile: scroll", scrollObject);
	 for(MobileElement parentCtrName:txt_ParentCategories1)
	 {
		 String parentName=parentCtrName.getText();
		 prtCtrNames.add(parentName);
   
		 System.out.println("Parent name is" + parentName);
  }
 }
 catch(Exception e)
 {
  System.out.println("Exception occured" + e.getMessage());
 }
 return prtCtrNames;
}


}
