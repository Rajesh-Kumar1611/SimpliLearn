package simpli.kvp.automate.org;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.jboss.netty.channel.StaticChannelPipeline;

import com.sforce.soap.partner.sobject.SObject;

import readtestdata.kvp.org.ReadData;
import readtestdata.kvp.org.StoreData;
import simpli.kvpsalesforceapi.org.SalesforceUtility;
import simplilearn.kvp.pojo.Lead;

public class MetaDataTest {

		public Map<String,String> entrypg = new HashMap<String,String>();
		
		
		public void checkmetadata(){
			
			SalesforceUtility.getConnection();
			
			//SalesforceUtility obj= new SalesforceUtility();
			ReadData readobj= new ReadData();
			readobj.getpath("D:/TestData2.xlsx");
			//obj.Insert("D:/TestData2.xlsx",2);
			
			readobj.getsheetindex(2);
			readobj.rowcount();
			readobj.getcolvalue();
			readobj.columndictionary();
			StoreData storeobj= new StoreData();
			Lead lead= new Lead();
			ArrayList<Lead>leaddata= new ArrayList<Lead>();
			leaddata=storeobj.createlist();
			StringBuilder s= new StringBuilder();
			s.append("(");
			for (Lead l:leaddata)
			{
				s.append("'");
				entrypg.put(l.getLastname(), l.getEntrypage());
				s.append(l.getLastname());
				s.append("'");
				s.append(",");
			
			}
			s.deleteCharAt(s.length()-1);
			s.append(")");
			leaddata.add(lead);
			System.out.println(s);
		System.out.println("/****************************************/");	
			

			
			//Map<String, String> leadid=new HashMap<String,String>();
			SObject[] datafrmsfdc=SalesforceUtility.getDetails("SELECT id,Entry_Page__c,CLF_Resource_Name__c,Cold_Latest_Site_Module__c,FirstName,LastName FROM Lead WHERE Status='Cold' AND LastName IN " +s);
			
			for(SObject data:datafrmsfdc)
			{
				//leadid.put((String)data.getField("Id"), (String)data.getField("LastName"));
				//System.out.println(data);
				entrypg.put((String)data.getField("Id"),(String)data.getField("FirstName")+data.getField("LastName"));
				System.out.println(data.getField("Id")+" "+data.getField("FirstName")+data.getField("LastName"));
			}
			
			Map<String, String> clf_status= new HashMap<String, String>();
			Map<String, String> clf_status1= new HashMap<String,String>();
			Map<String, String> clf_status2= new HashMap<String,String>();
			
			for(SObject ss :datafrmsfdc){
				clf_status.put((String)ss.getField("Id"), (String)ss.getField("CLF_Resource_Name__c"));
				
				//System.out.println(clf_status.get(ss));
			}
			
			System.out.println(clf_status);
			System.out.println("\n");
			
			for(SObject ss :datafrmsfdc){
				clf_status1.put((String)ss.getField("Id"), (String)ss.getField("Cold_Latest_Site_Module__c"));
			}
			System.out.println(clf_status1);
			System.out.println("\n");
			for(SObject ss :datafrmsfdc){
				clf_status2.put((String)ss.getField("Id"), (String)ss.getField("Entry_Page__c"));
			}
			System.out.println(clf_status2);
			System.out.println("\n");
			/*for(SObject ss :datafrmsfdc){
			
				System.out.println("Lead id="+ss.+" "+"CLF_Resource Name="+clf_status.get(ss)+ " "+ "ColdLatest Site Module="+clf_status1.get(ss)+ " "+"Entry Page=" +clf_status2.get(ss));
			}*/
			
			
			for(String key:clf_status.keySet()){
				System.out.println("Lead id="+key+", " +"CLF_Resource_Name="+clf_status.get(key)+", "+"Cold_Latest_Site_Module="+clf_status1.get(key)+" ,"+"Entry Page=" +clf_status2.get(key));
			}
			
			
			
		}
		public static void main(String[] args){
			MetaDataTest md= new MetaDataTest();
			md.checkmetadata();
		}
			
				
				
		
			
			
		}
			
	

