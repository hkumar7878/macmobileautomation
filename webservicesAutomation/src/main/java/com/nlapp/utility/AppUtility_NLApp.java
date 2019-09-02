package com.nlapp.utility;


import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;

import static io.restassured.path.json.JsonPath.from;

import com.cucumber.parallel.baseSteps.steps.NewBaseClass;

import io.appium.java_client.MobileElement;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import io.restassured.path.json.JsonPath;





public class AppUtility_NLApp extends NewBaseClass {

	public static String query;
	static Connection connection;
	static Statement stmt;
	static ResultSet rs;
	
	public static String response;
	public static List<String> names;
	
	//private static List<String> mismatchValues;
	public static void scrollDown_Android(){
		 
		 Dimension dimension= driver.manage().window().getSize();
		 Double scrollHeighStart=dimension.getHeight()*0.5;
		 
		 int scrollStart=scrollHeighStart.intValue();
		 Double scrollHeighEnd=dimension.getHeight()*0.2;
		 int scrollEnd=scrollHeighEnd.intValue();
		 new TouchAction((PerformsTouchActions)driver).press(PointOption.point(0, scrollStart))
		  .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1))).moveTo(PointOption.point(0, scrollEnd))
		  .release().perform();
		}

		public static List<WebElement> getItemInStoreProduct(){
		 return driver.findElements(By.xpath("android.widget.TextView[@text='Instore only products']"));
		}
		
		public static List<String> fetch_ViewGroup_Items_Android(List<MobileElement> prnt_Categories)
		{
		 //while(getItemInStoreProduct().size()==0)
		 //{
		  //scrollDown();
		 ////*[@class='android.view.ViewGroup']
		 //}
		 /*if(getItemInStoreProduct().size()>0)
		 {
		  String text=getItemInStoreProduct().get(0).getText();
		  System.out.println("Text is " + text);
		 }*/
		 List<String> app_Parent_Ctrgies= new ArrayList<String>();
		// List<WebElement> elements = driver.findElementsByClassName("android.widget.TextView");
		 scrollDown_Android();
		 int size=prnt_Categories.size();
		 for(int i=0;i<size;i++){
		  String text=prnt_Categories.get(i).getText();
		  app_Parent_Ctrgies.add(text);
		 }
		 scrollDown_Android();
		 int size1=prnt_Categories.size();
		 for(int i=0;i<size1;i++){
		  String text=prnt_Categories.get(i).getText();
		  if(!app_Parent_Ctrgies.contains(text))
		  app_Parent_Ctrgies.add(text);
		 }
		 System.out.println("Parent cateogories from app are--->" + app_Parent_Ctrgies);
		 return app_Parent_Ctrgies;
		}
	public static String[][] getAPIListValues(String subscriptionKeyName,String subscriptionKeyValue,String apiURL)
	{
		String [][] ctryArray1=null;
		
		try {
			
			response=given().header(subscriptionKeyName,subscriptionKeyValue).when().get(apiURL).asString();
			System.out.println("Response is ---->"+response);			
			JsonPath jp = new JsonPath(response);
			//io.restassured.path.json.JsonPath jp= new io.restassured.path.json.JsonPath(response);
			names = from(response).getList("categoryTree.name");			
				for (String prntCtryName : names) {
					System.out.println("Title is" + prntCtryName);
					apiParentCtryNames1.add(prntCtryName);
				}
			ctryArray1=GenericUtility_NLApp.convertListIntoTwoDimArray(apiParentCtryNames1);
			System.out.println("Parent Category list from API respose is ------------->" + apiParentCtryNames1);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return ctryArray1;
	}

	public static String[][] getSQLQueryData(String sqlQuery) {
		String [][] ctryArray1=null;

		try {
			query = sqlQuery;
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			connection = DriverManager
					.getConnection("jdbc:sqlserver://twgasql01d.database.windows.net;database=nlmobiletest;user=Support;password=H0llywood$;Integrated Security=true");
			stmt = connection.createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				System.out.println("Branch Code in Database is ----> "+ rs.getString("Name"));
				dbParentCtryNames.add(rs.getString("Name"));
			}
			//String ctryArray[][];
			 String ctryArray[][] = new String[dbParentCtryNames.size() + 1][1];

			for (int i = 0; i < dbParentCtryNames.size(); i++) {
				ctryArray[i + 1][0] = dbParentCtryNames.get(i);
				System.out.println("Value of two dimensional array is--------->" + ctryArray[i + 1][0]);
			}
			ctryArray1=ctryArray;
			
			
	} catch (Exception e) {
			System.out.println("Exception occured " + e.getMessage());
			return null;
		}
		return ctryArray1;
	
		
		
	}
	
	public static String[][] getSQLQueryData1(String sqlQuery) {
		//String [][] ctryArray;

		try {
			query = sqlQuery;
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			connection = DriverManager
					.getConnection("jdbc:sqlserver://twgasql01d.database.windows.net;database=nlmobiletest;user=Support;password=H0llywood$;Integrated Security=true");
			stmt = connection.createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				System.out.println("Branch Code in Database is ----> "+ rs.getString("Name"));
				dbParentCtryNames.add(rs.getString("Name"));
			}
			//String ctryArray[][] = new String[dbParentCtryNames.size() + 1][1];
			String ctryArray[][] = new String[dbParentCtryNames.size()][1];
			ctryArray=GenericUtility_NLApp.convertListIntoTwoDimArray(dbParentCtryNames);
			return ctryArray;
			
			
	} catch (Exception e) {
			System.out.println("Exception occured " + e.getMessage());
			return null;
		}		
	}
	
	
	public static String getSQLQueryData1(String sqlQuery,String colName){
		String s1;

		try {
			query = sqlQuery;
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			connection = DriverManager
					.getConnection("jdbc:sqlserver://twgasql01d.database.windows.net;database=nlmobiletest;user=Support;password=H0llywood$;Integrated Security=true");
			stmt = connection.createStatement();
			rs = stmt.executeQuery(query);
			s1=rs.getString(colName);
			return s1;
			
			
	} catch (Exception e) {
			System.out.println("Exception occured " + e.getMessage());
			return null;
		}	
	}
	
	public static void listComparison1(List<String> a, List<String> b)
	{
		/*//boolean flag=true;
		api.add("Photo & Video");
		api.add("Televisions");
		api.add("Health & Grooming");
		api.add("abc");
		
		db.add("Photo & Video");
		db.add("Health & Grooming");
		db.add("abc");*/
		
		Collections.sort(a);
		System.out.println("Sorted api list is" + a);
		Collections.sort(b);
		System.out.println("Sorted db list is" + b);
		System.out.println("sorted");
		boolean flag = true;
		for(int i=0;i<a.size();i++)
		{
			//if(db.contains(api.get(i)))
				//flag=true;
			if(!b.contains(a.get(i)))
			{
				mismatchValues.add(a.get(i));
				//flag=false;
			}
		}
		//return flag;
		
	}
}
