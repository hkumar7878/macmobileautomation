package com.nlapp.api;

import static io.restassured.RestAssured.given;
import static io.restassured.path.json.JsonPath.from;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import io.restassured.path.json.JsonPath;

import com.cucumber.grid.app.pages.Page_Products_NLAPP;
import com.cucumber.parallel.baseSteps.steps.NewBaseClass;
import com.cucumber.parallel.extent.ExtentTestManager;
import com.nlapp.utility.AppUtility_NLApp;

import cucumber.api.DataTable;
import cucumber.api.Scenario;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class TC_02_MiddleWare_BarCode_Verification extends NewBaseClass{
	protected Scenario scenario;
	static String scenarioName;
	static int i=0;
	String response;
	List<String> names;
	List<String> prntCateNames= new ArrayList<String>();
	Page_Products_NLAPP obj_Products;
	private String query;
	Connection connection;
	Statement stmt;
	ResultSet rs;
	List<String> dbParentCtryNames= new ArrayList<String>();
	List<String> apiParentCtryNames=new ArrayList<String>();
	String barCode;
	String itemId;
	
	
	@Then("^user access export\\.DemandWareProductCurrent DB table to fetch Dataelements for EAN-(\\d+) Products BarCodes$")
	public void user_access_export_DemandWareProductCurrent_DB_table_to_fetch_Dataelements_for_EAN_Products_BarCodes(int arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		System.out.println("Bar code are" + arg1);
		barCode=Integer.toString(arg1);
		String sqlQuery= "Select ItemId from [import].[CurrentBarcode] where Barcode='+"+barCode+"+'";
		itemId=AppUtility_NLApp.getSQLQueryData1(sqlQuery, "itemId");
	}

	@Then("^user access export\\.DemandWareProductCurrent DB table to fetch Dataelements for Returned Products BarCodes$")
	public void user_access_export_DemandWareProductCurrent_DB_table_to_fetch_Dataelements_for_Returned_Products_BarCodes() throws Throwable {
	    
	}

	@Given("^user access ScanBarcode JSON to fetch API response values for EAN-(\\d+) BarCodes$")
	public void user_access_ScanBarcode_JSON_to_fetch_API_response_values_for_EAN_BarCodes(int arg1) throws Throwable {
		response=given().header("Ocp-Apim-Subscription-Key","c68fa40a7cb443ecbba9866f63e9edf2").when()
				.get("https://twg.azure-api.net/twlYourWarehouseTest/ScanBarcode.json?Barcode=barCode&BoltUserId"
						+ "=798FD058-D2D9-4BE3-9606-9A577734ED7A&BranchId=null")
				//.get("https://twg.azure-api.net/twlYourWarehouseTest/Category.json?CategoryId=12704-mobilephones-smartphone")
			.asString();
		int statusCode=given().header("Ocp-Apim-Subscription-Key","c68fa40a7cb443ecbba9866f63e9edf2").when()
				.get("https://twg.azure-api.net/twlYourWarehouseTest/Category.json?CategoryId=12704-mobilephones-smartphone")
			.statusCode();
		System.out.println("Status code is" + statusCode);
		System.out.println("Response is ---->"+response);
		
		if (statusCode == 200) {
			JsonPath jp = new JsonPath(response);			
			names = from(response).getList("categoryTree.name");			
			for (String prntCtryName : names) {
				System.out.println("Title is" + prntCtryName);
				apiParentCtryNames.add(prntCtryName);
				
			}
			System.out.println("Parent Category list from API respose is ------------->" + apiParentCtryNames);
			
			ExtentTestManager.logInfo("Parent Categories names fetched from GetCategory API response are " + apiParentCtryNames);
		}
		else
			ExtentTestManager.logFail("GetCategory API not reachable");

	}

	@Then("^verify the ScanBarCode JSON API response values for EAN (\\d+) BarCodes$")
	public void verify_the_ScanBarCode_JSON_API_response_values_for_EAN_BarCodes(int arg1) throws Throwable {
	    
	}
}
