package simpli.kvp.automate.org;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import readtestdata.kvp.org.ReadData;
import readtestdata.kvp.org.StoreData;
//import readtestdata.kvp.org.TakeScreenshot;
import simplilearn.kvp.pojo.Lead;

public class TestLead 
{
	
	

	/****************Create objects for class belonging to other packages**********************/
	

	
	//public Object[][] data;
	
	public HashMap <String,String> lead_comp= new HashMap <String, String>();
	
	//public Hashmap <String, String> dateval= new TreeMap<>
	
	StoreData storedataobj =new StoreData();
	
	//TakeScreenshot scrnsht= new TakeScreenshot();
	
	ArrayList<Lead> leaddata =new ArrayList<Lead>();
/******************************************************************/	

	
	
WebDriver driver1;
String username ="tushar.bansal@simplilearn.com.newqa";
String pass="Qasb@123";

String baseURL= "http://test.salesforce.com";

String ret="";
//ITestResult result;






//To test the login functionality into Salesforce.


@Test(priority=0)

public void testlogin() throws Throwable
{
	ReadData readobj =new ReadData();
  //ReadData data1;
  System.setProperty("webdriver.chrome.driver","D:/chromedriver.exe");
  
  driver1=new ChromeDriver();
  
  driver1.manage().window().maximize();
  
  driver1.get(baseURL);
  	    
  driver1.findElement(By.xpath(".//*[@id='username']")).sendKeys(username);
  driver1.findElement(By.xpath(".//*[@id='password']")).sendKeys(pass);
  driver1.findElement(By.xpath(".//*[@id='Login']")).click();
  System.out.println("Login Successfull");
  
  driver1.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
  
  
  
  
  /*********************************************Excel Data Validation*********************************************/
  
  
  
  	//readobj=new ReadData("E:/TestData.xlsx");
  
  			readobj.getpath("D:/TestData2.xlsx");
	 
  			readobj.getsheetindex(0);
	
  				System.out.println("Total number of rows in the sheet are  " + readobj.rowcount());
	
  						System.out.println("Total number of columns in the sheet are  " + readobj.getcolvalue());
	
  						
  						Thread.sleep(4000);
	
	  			
  						readobj.columndictionary();
	
	
	
	//System.out.println(readtestdata.getCell("website"));
	
	  			  			
  								leaddata=storedataobj.createlist();
	
}
	
		@Test(priority=1)
		public void leaddata() throws Exception   
		
		{
			
			try
			{
			
			//String[] str=null;
			//ReadData readobj=new ReadData();
	
	//Map<String,String> leademailtostatus=new HashMap<>();		
	for(Lead l:leaddata)
		
	{
		
		//leademailtostatus.put(l.getEmail(), l.getLeadstatus());
		System.out.println(l.getFirstname());	
		
		
						
						driver1.findElement(By.xpath("//*[@id='Lead_Tab']/a")).click();
						  
						Thread.sleep(3000);
	  
					driver1.findElement(By.xpath(".//input[@class='btn'][@title='New']")).click();
	  
											WebElement rec_type_dp1= driver1.findElement(By.xpath(".//*[@id='p3']"));
	  
														Select rec_type_value =new Select(rec_type_dp1);
				
																rec_type_value.selectByIndex(0); //To select the record type(B2C for Lead)
				
																	driver1.findElement(By.xpath(".//*[@title='Continue']")).click();
																	
							
																	driver1.manage().timeouts().implicitlyWait(62,TimeUnit.SECONDS);
																	
																	
																	
			/***************************************Data Insertion**************************/	
																	
																	
			driver1.findElement(By.xpath("//label [contains(text(), 'First Name')]//following::td[1]/input")).sendKeys(l.getFirstname());

			driver1.findElement(By.xpath("//label[contains(text(), 'Last Name')]//following ::td[1]/div/input")).sendKeys(l.getLastname());
		
			try{
				
			driver1.findElement(By.xpath(".//label[starts-with(text(), 'Email')]//following::td[1]/div/input")).sendKeys(l.getEmail());
				if(l.getEmail().equals(null))
				{
					System.out.println("Continue searching");
				}
			}
				catch(Exception e){
					System.out.println(e.getMessage());
				}
											
								
											driver1.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
				
			
											WebElement leadsrc=driver1.findElement(By.xpath(".//label[contains(text(), 'Lead Source')]//following::select[1]"));
			
											Select lead_rec= new Select(leadsrc);
			
											lead_rec.selectByValue(l.getLeadsource());
											
				/*****************************************1> Scenario--Referral Check**************************************************************/							
											
					try
					{
			if(l.getLeadsource().equals("Reference"))
			{
				if(l.getRefemail().equals(null)||l.getRefname().equals(null))
				{
					System.out.println("Continue searching next element in the object");
				}
				else
				{
				
				System.out.println("Checking the referral scenario");
				driver1.findElement(By.xpath("//label[contains(text(),'Referral Email')]//following::input[1]")).sendKeys(l.getRefemail());
				driver1.findElement(By.xpath("//label[contains(text(),'Referral Name')]//following::input[1]")).sendKeys(l.getRefname());
				}
			}
					}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
				
				
				
			
			
			//scrnsht.captureScreenShot(driver1);
			
			
			/*****************************************1> Scenario--Referral Check Ends**************************************************************/					
			
					WebElement leadstatus=driver1.findElement(By.xpath("//label[contains(text(),'Lead Status')]//following::select[1]"));
					
					Select leadstat_val=new Select(leadstatus);
					
					leadstat_val.selectByValue(l.getLeadstatus());
			
											try{
												driver1.findElement(By.xpath(".//label[contains(text(),'Website')]//following::input[1]")).sendKeys(l.getWebsite());
					  			 				
												if(l.getWebsite().equals(null))
												{
													System.out.println("Moved to next element");
												}
											}
											catch(Exception e){
												System.out.println(e.getMessage());
											}
										
											
											
											
											driver1.findElement(By.xpath("//label[contains(text(),'Country')]//following::input[1]")).sendKeys(l.getCountry());
												
											driver1.findElement(By.xpath("//label[contains(text(),'Primary Category of Interest')]//following::select[1]")).sendKeys(l.getPcategory());
			
											driver1.findElement(By.xpath("//label[contains(text(),'Primary Training Type')]//following::select[1]")).sendKeys(l.getPtraining());
			
			
			//((JavascriptExecutor)driver1).executeScript("scrollBy(0,2000)");
			
			
			driver1.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
			
				
			//Boolean status=course_chkbx.isDisplayed();   //For information purpose			
			
			//Boolean status1=course_chkbx.isEnabled();
			
			//System.out.println("To check if the Primary Courses checkbox are enabled & displayed=" +status +","+  " "+status1); //to get the status of the checkbox
			
			//Thread.sleep(10000);
			
			//((JavascriptExecutor)driver1).executeScript("window.scrollTo(0,"+course_chkbx.getLocation().x+")");
			
			
			//Actions act_chkbx= new Actions(driver1);
			  			 			 						
			//act_chkbx.click(course_chkbx).build().perform();
			
			//scrnsht.captureScreenShot(driver1);
			
			
			WebElement save_button=driver1.findElement(By.xpath(".//*[@id='bottomButtonRow']/input[1]"));
			save_button.click();
			
			Thread.sleep(10000);
			
			//Assert.assertEquals(driver1.findElement(By.xpath("//*[@id='topButtonRow']/input[3]")), driver1.findElement(By.xpath(".//*[@id='errorDiv_ep']")));
			
			
				
	}
	
			}
	
	catch(Exception e)
	
	{
		System.out.println(e.getMessage());
	}
	
			
		}	
		
	
			
		
		@Test(priority=2)
		
		public void checkstatus()
		{
			
			TestStatus checkstat= new TestStatus();
			
			checkstat.checkstatchange();
		}
				
					
				
				
				}
				
				


