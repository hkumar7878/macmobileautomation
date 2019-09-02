package com.nlapp.utility;

import static io.restassured.RestAssured.given;
import java.util.List;
import org.openqa.selenium.WebElement;
import com.cucumber.parallel.baseSteps.steps.NewBaseClass;

public class GenericUtility_NLApp extends NewBaseClass{

	
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
