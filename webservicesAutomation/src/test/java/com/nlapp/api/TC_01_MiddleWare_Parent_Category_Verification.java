package com.nlapp.api;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.testng.Assert;

import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.cucumber.grid.app.pages.Page_Home_NLAPP;
import com.cucumber.grid.app.pages.Page_Products_NLAPP;
import com.cucumber.parallel.baseSteps.steps.NewBaseClass;
import com.cucumber.parallel.extent.ExtentTestManager;
import com.nlapp.utility.AppUtility_NLApp;
import com.nlapp.utility.GenericUtility_NLApp;

import cucumber.api.DataTable;
import cucumber.api.Scenario;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class TC_01_MiddleWare_Parent_Category_Verification extends NewBaseClass{

protected Scenario scenario;
static String scenarioName;
static int i=0;
String response;
List<String> names;
List<String> device_prntCateNames= new ArrayList<String>();
Page_Products_NLAPP obj_Products;
private String query;
Connection connection;
Statement stmt;
ResultSet rs;
static List<String> dummy_DB_Parent_Categories= new ArrayList<String>();

String[][] apiParentCtryNames;
static String [][] dbParentCtryNames;


@Given("^user access DB and fetch category data$")
public static void user_access_DB_and_fetch_category_data(DataTable sqlQuery) throws Throwable {
 
 /*List<List<String>> data = sqlQuery.raw();
 dbParentCtryNames=AppUtility_NLApp.getSQLQueryData1(data.get(0).get(0));
 System.out.println("DATABASE PARENT CATEGORIES ARE---------------->"+dbParentCtryNames);
 if(dbParentCtryNames!=null)
 {
  ExtentTestManager.logInfo(MarkupHelper.createLabel("Parent Categories from DB : ", ExtentColor.BLUE));
  ExtentTestManager.logInfo(MarkupHelper.createTable(dbParentCtryNames));
 }
 else
 {
  
 }*/
 dummy_DB_Parent_Categories.add("Televisions");
 dummy_DB_Parent_Categories.add("Audio");
 dummy_DB_Parent_Categories.add("Photo & Video");  
 dummy_DB_Parent_Categories.add("Health & Grooming");
 dummy_DB_Parent_Categories.add("Drones");
 dummy_DB_Parent_Categories.add("Gaming & Toys");
 dummy_DB_Parent_Categories.add("Phones & GPS");
 dummy_DB_Parent_Categories.add("Household Appliances");
 dummy_DB_Parent_Categories.add("Computers & Tablets");
 dummy_DB_Parent_Categories.add("Instore only products");
 dummy_DB_Parent_Categories.add("Wearables"); 
 dummy_DB_Parent_Categories.add("Whiteware & Kitchen Appliances");
 
        }
@Given("^user access Category API and fetch category data$")
public void user_access_Category_API_and_fetch_category_data(DataTable apiReqData) throws Throwable {
 try {
  List<List<String>> data = apiReqData.raw();
  
  int statusCode=GenericUtility_NLApp.getStatusCode(data.get(0).get(0), data.get(0).get(1), data.get(0).get(2));
  
  Assert.assertEquals(200, statusCode);
  apiParentCtryNames= AppUtility_NLApp.getAPIListValues(data.get(0).get(0), data.get(0).get(1), data.get(0).get(2));
  if(apiParentCtryNames!=null)
  {
   ExtentTestManager.logInfo(MarkupHelper.createLabel("Parent Category fetched from API are as follow", ExtentColor.BLUE));
   ExtentTestManager.logInfo(MarkupHelper.createTable(apiParentCtryNames));
  }
  
  else
  {
   
  }
 } catch (AssertionError e) {
  
  e.printStackTrace();
 }
    
}

@Given("^user launches the app$")
public void user_launches_the_app() throws Throwable {
   
 System.out.println("Launching the app");
   
}

@Then("^user clicks on the Products tab$")
public void user_clicks_on_the_Products_tab() throws Throwable {
 try{
 System.out.println("Test");
 Page_Home_NLAPP obj_HomePage= new Page_Home_NLAPP(driver);
 ExtentTestManager.logInfo("Clicking Products tab");
 
 obj_Products=obj_HomePage.clickTab_Product();
 
 }
 catch(Exception e)
 {
  System.out.println("Exception occured" + e.getLocalizedMessage());
  ExtentTestManager.logFail("Products tab is not clicked due to exception" + e.getMessage());;
 }
   
}

@Then("^verify Products tab is displayed$")
public void verify_Products_tab_is_displayed() throws Throwable {
 try {
  obj_Products= new Page_Products_NLAPP(driver);
  boolean flag=obj_Products.verifyProductsTab();
  Assert.assertTrue(flag);
  System.out.println("Products tab is clicked successfully");
  ExtentTestManager.logPass("Product is clicked successfully");
 } catch (AssertionError e) {
  e.printStackTrace();
  ExtentTestManager.logFail("No Product tab is displayed");
 }
 
 //return;
   
}


@Then("^all categories on Product tab is fetched$")
public void all_categories_on_Product_tab_is_fetched() throws Throwable {
 //AppUtility_NLApp.fetch_ViewGroup_Items_Android();
 device_prntCateNames=obj_Products.getParentCategoryNames1();
 String [][] appCtryList=GenericUtility_NLApp.convertListIntoTwoDimArray(device_prntCateNames);
 if(appCtryList!=null)
 {
  ExtentTestManager.logInfo(MarkupHelper.createLabel("Parent Category fetched from NL Mobile application are", ExtentColor.BLUE));
  ExtentTestManager.logInfo(MarkupHelper.createTable(appCtryList));
 }
 
 else
 {
  
 }
 System.out.println("Parent Category names are----->" + device_prntCateNames );
 ExtentTestManager.logInfo("Parent categories fetched from the NLAPP are" + device_prntCateNames);
 
 
    
}

@Then("^verify the parent categories on the app with the response$")
public void verify_the_parent_categories_on_the_app_with_the_response() throws Throwable {
 //assertThat(names).containsAll(prntCateNames);
 try{
  Collections.sort(apiParentCtryNames1);
  Collections.sort(device_prntCateNames);
 Assert.assertEquals(apiParentCtryNames1, device_prntCateNames);
 ExtentTestManager.logPass("Parent Categories from both api response and mobile app are matching" + "API parent"
   + " are " + apiParentCtryNames1 + "  and App parent category is -->" + device_prntCateNames);
 }
 catch(AssertionError e)
 {
  System.out.println("Exception occured" + e.getMessage());
  AppUtility_NLApp.listComparison1(apiParentCtryNames1, device_prntCateNames);
  if(apiParentCtryNames1.size()>device_prntCateNames.size()){
  ExtentTestManager.logFail("Parent Categories from both api response and mobile app NOT are matching" + "   API parent"
    + " are " + apiParentCtryNames + " and App parent category is -->" + device_prntCateNames);
  ExtentTestManager.logInfo("API Parent Categories which are not shown on device are " + mismatchValues);
  Assert.fail();
  }
  else
  {
   ExtentTestManager.logFail("Parent Categories from both api response and mobile app NOT are matching" + "API parent"
     + " are " + apiParentCtryNames + " and App parent category is -->" + device_prntCateNames);
   ExtentTestManager.logInfo("Extra parent categories not present in API shown on device are " + mismatchValues);
   Assert.fail();
  }
 }
 }
}