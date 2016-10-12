package simpli.kvp.automate.org;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import com.sforce.soap.partner.sobject.SObject;

import readtestdata.kvp.org.ReadData;
import readtestdata.kvp.org.StoreData;
import simpli.kvpsalesforceapi.org.SalesforceUtility;
import simplilearn.kvp.pojo.Lead;

public class TestStatus {
	

	 
	public void checkstatchange()
	{	
     Map<String, String> oldstatus= new HashMap<String,String>();
	 Lead lead= new Lead();
	 ReadData readobj= new ReadData();
	 StoreData storeobj= new StoreData();
	 ArrayList<Lead> leaddata= new ArrayList<Lead>();
		readobj.getpath("D:/TestData2.xlsx");
		readobj.getsheetindex(1);
		readobj.rowcount();
		readobj.getcolvalue();
		readobj.columndictionary();
		leaddata=storeobj.createlist();
		
		/*for(int i=0;i<5;i++)
		{
		Lead l =new Lead();
		l.setLastname("Kumar"+i);
		leaddata.add(l);
		}*/
		
		
		StringBuilder s = new StringBuilder();
		s.append("(");
		for(Lead l:leaddata)
		{
			oldstatus.put(l.getLastname(), l.getLeadstatus());
			s.append("'");
			s.append(l.getLastname());
			s.append("'");
			s.append(",");
		
		}
		s.deleteCharAt(s.length()-1);
		s.append(")");
		leaddata.add(lead);
		
		
//		System.out.println("Before Insert "+oldstatus);
		//Now insert lead into SFDC
		
		SalesforceUtility.getConnection();
		SalesforceUtility obj= new SalesforceUtility();
		obj.Insert("D:/TestData2.xlsx",2);
		
		//Now get the updated Lead status
		
		SObject[] datafromsf=SalesforceUtility.getDetails("select id,LastName,Status from Lead where LastName IN "+s);
		Map<String,String> mapdatafromsf=new TreeMap<String,String>();
		for(SObject ss:datafromsf)
		{
			mapdatafromsf.put((String)ss.getField("LastName"),(String)ss.getField("Status"));
		}
		
//		System.out.println("After Insert "+mapdatafromsf);
		
		
		System.out.println("To verify the Lead Status Change for the cold leads after insert");
	    for(String key:oldstatus.keySet())
	    {
	    	System.out.println("Lead Name: "+key +" Old Status: "+oldstatus.get(key)+" New Status: "+mapdatafromsf.get(key));
	    	
	    }
		
	}
	
	public static void main(String[] args) {
		TestStatus obj=new TestStatus();
		obj.checkstatchange();
	}
	
	

}
