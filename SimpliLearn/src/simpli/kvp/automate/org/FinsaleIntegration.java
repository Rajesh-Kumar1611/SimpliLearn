package simpli.kvp.automate.org;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import readtestdata.kvp.org.StoreData;
import simpli.kvpsalesforceapi.org.SalesforceUtility;
import simplilearn.kvp.pojo.Order;
import com.sforce.soap.partner.sobject.SObject;
import com.sforce.ws.bind.XmlObject;
class FinsaleIntegration{
		public void checkfin(){
			ArrayList<Order> orderdata= new ArrayList<>();
			Set<String> orderid= new HashSet<>();
			Order order= new Order();
			HashMap<String,Integer> lineitemcount= new HashMap<String,Integer>();
			HashMap<String,Integer> afterinsertcount= new HashMap<String,Integer>();
			SalesforceUtility.getConnection();
			StoreData store= new StoreData();
			/*
			ReadData readobj = new ReadData();			
			readobj.getpath("D:/TestData2.xlsx");
			readobj.getsheetindex(3);
			readobj.rowcount();
			readobj.getcolvalue();`
			readobj.columndictionary();
			System.out.println("Row Count "+readobj.rowcount());
			*/
			orderdata=store.createorderlist();
			
			StringBuilder sb= new StringBuilder();
			sb.append("(");
			
			for (Order o:orderdata){
				orderid.add(o.getOrderid());
				sb.append("'");
				sb.append(o.getOrderid());
				sb.append("'");
				sb.append(",");
			}
			
			sb.deleteCharAt(sb.length()-1);
			sb.append(")");
			orderdata.add(order);
			System.out.println(sb);
			
			SObject [] datafrmsfdc= SalesforceUtility.getDetails("Select Name,(select Id from LineItems__r) from Order__c where Order_Id__c in "+ sb);
			
			for(SObject data:datafrmsfdc){
				
					XmlObject xmml=(XmlObject)data.getField("LineItems__r");
					lineitemcount.put(data.getField("Name").toString(),Integer.valueOf(xmml.getChild("size").getValue().toString()));
				}
				//lineitemcount.put((String)data.getField("Name"), (String)data.getField("Id"));
			
			
			
			//After Insert get the Line items count and compare with the previous one.
			
SObject [] afterinsert= SalesforceUtility.getDetails("Select Name,(select Id from LineItems__r) from Order__c where Order_Id__c in "+ sb);
			
			for(SObject data:afterinsert){
				
					XmlObject xmml=(XmlObject)data.getField("LineItems__r");
					afterinsertcount.put(data.getField("Name").toString(),Integer.valueOf(xmml.getChild("size").getValue().toString()));
				}
			
			
			System.out.println(afterinsertcount);
			
			for(String comp:lineitemcount.keySet()){
				if(lineitemcount.values().equals(afterinsertcount.values())){
					System.out.println("Test Failed");
				}
				else{
					System.out.println("Test Passed");
				}
				
			}
			
			
			
			
		}
		
		
		
		public static void main(String[] args) {
			
			
			FinsaleIntegration fn= new FinsaleIntegration();
			fn.checkfin();
		}
		
	}