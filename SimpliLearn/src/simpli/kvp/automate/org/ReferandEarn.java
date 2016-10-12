package simpli.kvp.automate.org;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import readtestdata.kvp.org.ReadData;
import readtestdata.kvp.org.StoreData;
import simpli.kvpsalesforceapi.org.SalesforceUtility;
import simplilearn.kvp.pojo.Lead;

import com.sforce.soap.partner.sobject.SObject;

public class ReferandEarn {
	
	public void getreferrerdetails(){
				
		ReadData readobj= new ReadData();
		StoreData storeobj= new StoreData();
//		Lead lead= new Lead();
		readobj.getpath("D:/TestData2.xlsx");
		readobj.getsheetindex(4);
		readobj.rowcount();
		readobj.getcolvalue();
		ArrayList <Lead> referrered = new ArrayList<Lead>();
		readobj.columndictionary();
		referrered=storeobj.createlist();
		ArrayList <String>referreremail= new ArrayList<String>();
		Map<String,String> emailidmap = new HashMap<String,String>();
		StringBuilder sb= new StringBuilder();
		sb.append("(");
		for(Lead l:referrered){
			sb.append("'");
			referreremail.add(l.getRefemail());
			sb.append(l.getRefemail());
			sb.append("'");
			sb.append(",");
			emailidmap.put(l.getEmail(),l.getRefemail());
		
		}
		sb.deleteCharAt(sb.length()-1);
		sb.append(")");		
			
		HashMap<String,String> referrerdata= new HashMap<String,String>();
		
		HashMap<String, String> freshlead= new HashMap<String,String>();
		
		
		SalesforceUtility.getConnection();
		
		SObject[] emailquery= SalesforceUtility.getDetails("Select Id,Status,Alternate_Email__c ,Email,Primary_Course_Interested__c from Lead where Status='New' and email in "+ sb);
		
		//Fetching the referred details from CRM and storing in map
		for(SObject data:emailquery){
		
			referrerdata.put((String)data.getField("Email"),(String)data.getField("Primary_Course_Interested__c"));
		}
			
		System.out.println("Referrer Email Id:"+referrerdata.keySet()+ " ,"+ "Primary Course:" +referrerdata.values());
		
		
		System.out.println("Now a Map would show the New Lead's email and Primary Course before getting Overridden");
		
		// Reading referred data from excel and storing it in map
		for(Lead l1:referrered){
			
			freshlead.put(l1.getEmail(),l1.getPcategory());
			
		}
		System.out.println("Fresh Lead's email:"+freshlead.keySet()+ " ," +"Fresh Lead's Primary Training:"+freshlead.values());
		
		
		
		System.out.println("Please create leads manually with referral through API to get the course overridden for Site Module-Refer and Earn");
		
		//ArrayList<String>freshleademail= new ArrayList<String>();
		
		Map<String,String> newleadvaluesmap = new HashMap<String,String>();
		
		StringBuilder sb_referred_email= new StringBuilder();
		sb_referred_email.append("(");
		for(String email:freshlead.keySet()){
			sb_referred_email.append("'");
			sb_referred_email.append(email);
			sb_referred_email.append("'");
			sb_referred_email.append(",");
		
		}
		sb_referred_email.deleteCharAt(sb_referred_email.length()-1);
		sb_referred_email.append(")");	
		
		SObject[] freshquery= SalesforceUtility.getDetails("Select Id,Email,Primary_Course_Interested__c from Lead where Status='New' and email in "+sb_referred_email);
		
		System.out.println("Fresh Leads Primary Course after insertion through API:");
		
		for (SObject data:freshquery){
			
			//freshleademail.add((String)data.getField("Primary_Course_Interested__c"));
			newleadvaluesmap.put((String)data.getField("Email"), (String)data.getField("Primary_Course_Interested__c"));
			
		}
		System.out.println(newleadvaluesmap);
		System.out.println("New status of the fresh lead");
		for(String s:newleadvaluesmap.keySet())
		{
		System.out.println(newleadvaluesmap.get(s));	
		}
		
		for(String s:freshlead.keySet())
		{
			System.out.println("Before Insert");
			System.out.println("Email : "+s +","+" Course :"+freshlead.get(s));
			System.out.println("Referrer's Email Id :"+emailidmap.get(s)+","+" Referrer's Course :"+referrerdata.get(emailidmap.get(s)));
			System.out.println("After Insert");
			System.out.println("Lead's Email Id : "+s + ","+" Lead's Course :"+newleadvaluesmap.get(s));
			
			
			
			
			
		}
		//`HashMap<String, String> checkcourse= new HashMap<String,String>();
	
		
		
		
		
		
		
}
	
	
	
	
	public static void main(String[] args) {
		ReferandEarn rfe= new ReferandEarn();
		rfe.getreferrerdetails();
		
	}
}
