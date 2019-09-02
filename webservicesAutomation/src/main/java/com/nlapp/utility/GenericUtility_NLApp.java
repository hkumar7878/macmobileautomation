package com.nlapp.utility;


import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import com.cucumber.parallel.baseSteps.steps.NewBaseClass;

import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

import static io.restassured.RestAssured.*;


public class GenericUtility_NLApp extends NewBaseClass{
	
	public void swipeAndScroll(int x_start,int y_start,int x_stop,int y_stop,int duration) {
		new TouchAction(driver).press(PointOption.point(x_start,y_start)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(duration)))
		.moveTo(PointOption.point(x_stop,y_stop)).release().perform();
		
	}

	public static void scrollDown(){
		 
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

		public static void scrollIntoDesiredElement()
		{
		 while(getItemInStoreProduct().size()==0)
		 {
		  scrollDown();
		 }
		 if(getItemInStoreProduct().size()>0)
		 {
		  String text=getItemInStoreProduct().get(0).getText();
		  System.out.println("Text is " + text);
		 }
		}
	
	public static String[][] convertListIntoTwoDimArray(List<String> list)
	{	
		String ctryArray[][] = new String[list.size() + 1][1];		
		try {
			for (int i = 0; i < list.size(); i++) {
				ctryArray[i][0] = list.get(i);
				System.out.println("Value of two dimensional array is--------->" + ctryArray[i + 1][0]);
			}			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ctryArray;
	}
	
	public static int getStatusCode(String subscriptionKeyName,String subscriptionKeyValue,String apiURL)
	{
		int statusCode=given().header(subscriptionKeyName,subscriptionKeyValue).when()
				.get(apiURL)
			.statusCode();
		return statusCode;
	}
	
	public boolean listComparison(List<String> a, List<String> b)
	{
		boolean flag = false;
		for(int i=0;i<a.size();i++)
        {
            String actualItem=(String)a.get(i);

            for (int j=0;j<b.size();j++)
            {
                String expItem=(String)b.get(j);
                int result = actualItem.compareTo(expItem);
                if(result==0)
                {
                    System.out.println(actualItem + " = " + expItem);
                    flag=true;
                }

                else
                {
                    flag=false;
                }
            }
        }
		return flag;
		
	}
	
	/*	
 	''@###########################################################################################################################
	''@Function ID: 
	''@Function Name: clickElement
	''@Objective: This function verifies content tab present on the page		
	''@---------------------------------------------------------------------------------------------------------------------------
	''@Param Name: 
	''@Param Name: 
	''@Param Name: 
	''@---------------------------------------------------------------------------------------------------------------------------
	''@Return Desc: 
	''@     Success - True
	''@     Failure - False
	''@---------------------------------------------------------------------------------------------------------------------------
	''@Example: blnStatus= clickElement()
	''@---------------------------------------------------------------------------------------------------------------------------
	''@Created by[Date]: 
	''@---------------------------------------------------------------------------------------------------------------------------
	''@Reviewed by[Date]: 
	''@---------------------------------------------------------------------------------------------------------------------------
	''@History Notes: 
	''@---------------------------------------------------------------------------------------------------------------------------
	''@###########################################################################################################################
	*/
	public static boolean clickElement (WebElement element)
	{
		//String ErrDescription = "";
		try{
		
		element.click();;
		return true;
		}
		catch(Exception e){
			ErrDescription = e.getMessage();
			return false;
			}
	}
}
