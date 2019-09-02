package Debugging;

import org.testng.annotations.Test;

import com.cucumber.grid.app.pages.Page_Products_NLAPP;
import com.cucumber.parallel.baseSteps.steps.NewBaseClass;
import com.cucumber.parallel.extent.ExtentTestManager;

import cucumber.api.Scenario;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TC_01_DatabaseConnectivity extends NewBaseClass{
	protected Scenario scenario;
	static String scenarioName;
	static int i=0;
	String response;
	List<String> names;
	List<String> prntCateNames;
	Page_Products_NLAPP obj_Products;
	private String query;
	Connection connection;
	Statement stmt;
	ResultSet rs;
	List<String> dbParentCtryNames= new ArrayList<String>();
	List<String> apiParentCtryNames;
	
	//@Test
	public void dbConnect() throws ClassNotFoundException, SQLException
	{
		try{
			query = "SELECT * FROM [import].[EP_Category] where ParentCategoryPath is null and Name not in ('Clearance Centre','Gift Card')"; 
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			connection = DriverManager.getConnection("jdbc:sqlserver://twgasql01d.database.windows.net;database=nlmobiletest;user=Support;password=H0llywood$;Integrated Security=true");
	        stmt = connection.createStatement();
	        rs = stmt.executeQuery(query);
	        while(rs.next()){
	        	System.out.println("Branch Code in Database is ----> "+rs.getString("Name"));
	        	String name=rs.getString("Name");
	        	//dbParentCtryNames.add(rs.getString("Name"));
	        	dbParentCtryNames.add(name);
	        	System.out.println("Testing");
	          }
	        System.out.println("DB list values are " + dbParentCtryNames);
	        ExtentTestManager.logInfo("Parent Categories names fetched from DB are " + rs);
			}
			
			
	        catch (Exception e)
	        {
	        	System.out.println("Exception occured " + e.getMessage());
	        }
	}

	@Test
	public void array()
	{
		try {
			List<String> list= new ArrayList<String>();
			list.add("Hitesh");
			list.add("Ghai");
			int size=list.size();
			System.out.println("List size is " + list.size());
			String ctryArray [][] = new String[size+1][1];
			ctryArray[0][0]="Category Values";
			System.out.println("First col value is" +ctryArray[0][0] );
			for(int i=0;i<list.size();i++)
			{
				ctryArray[i+1][0]=list.get(i);
				System.out.println("Value of two dimensional array is--------->"+ctryArray[i+1][0]);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
}
