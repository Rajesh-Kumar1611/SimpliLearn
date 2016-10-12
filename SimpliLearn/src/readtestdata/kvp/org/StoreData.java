package readtestdata.kvp.org;

import java.util.ArrayList;

import simplilearn.kvp.pojo.Lead;
import simplilearn.kvp.pojo.Order;

public class StoreData 
{
public ArrayList<Order> createorderlist()
{
		ReadData readorders = new ReadData();			
		readorders.getpath("D:/TestData2.xlsx");
		readorders.getsheetindex(3);
		readorders.columndictionary();
		ArrayList<Order> orderlist =new ArrayList<>();

	 for (int rcount=1;rcount <readorders.rowcount();rcount++)
	 	
	 {
	 	Order order =new Order();
	 	for (int col=0;col<readorders.getcolvalue();col++)		
	 	{
	 	//System.out.println("orderid"+readorders.getdata(rcount,readorders.getCell("orderid")));
	 	order.setOrderid((readorders.getdata(rcount, readorders.getCell("orderid"))));
	 	//order.setOrdername(readorders.getdata(rcount, readorders.getCell("ordername")));
	 	order.setEmailid(readorders.getdata(rcount, readorders.getCell("emailid")));
	 	//order.setLineitems(ArrayList<String>(readorders.getdata(rcount, readorders.getCell("lineitems"))));
	 	
	 	/*
	 	firstname.add(readorders.getdata(rcount,readorders.getCell("ptraining")));
	 	
	 	lastname.add(readorders.getdata(rcount, readorders.getCell("lastname")));
	 	
	 	email.add(readorders.getdata(rcount, readorders.getCell("email")));
	 	
	 	leadsource.add(readorders.getdata(rcount, readorders.getCell("leadsource")));
	 	
	 	country.add(readorders.getdata(rcount, readorders.getCell("country")));
	 	
	 	ptraining.add(readorders.getdata(rcount, readorders.getCell("ptraining")));
	 	
	 	//leadsource.add(readtestdata.getdata(rcount, readtestdata.getCell("website")));
	 	*/
	 	
	 	}
	 	orderlist.add(order);
	 }	
	 	return orderlist;		
	 	
	 }






public ArrayList<Lead> createlist()
	
	{
	 ReadData readobj =new ReadData();
	
	 
//*****************Get the Data from the excel**********************//
	
ArrayList<Lead> leadlist =new ArrayList<>();

for (int rcount=1;rcount <readobj.rowcount();rcount++)
	
{
	Lead lead =new Lead();
	
	for (int col=0;col<readobj.getcolvalue();col++)
		
	{
	
	lead.setCountry(readobj.getdata(rcount, readobj.getCell("country")));
	lead.setFirstname(readobj.getdata(rcount, readobj.getCell("firstname")));
	lead.setLastname(readobj.getdata(rcount, readobj.getCell("lastname")));
	lead.setEmail(readobj.getdata(rcount, readobj.getCell("email")));
	lead.setLeadsource(readobj.getdata(rcount, readobj.getCell("leadsource")));
	lead.setRefemail(readobj.getdata(rcount, readobj.getCell("refemail")));
	lead.setRefname(readobj.getdata(rcount,readobj.getCell("refname")));
	lead.setPtraining(readobj.getdata(rcount, readobj.getCell("country")));
	lead.setWebsite(readobj.getdata(rcount,readobj.getCell("website")));
	lead.setLeadstatus(readobj.getdata(rcount,readobj.getCell("leadstatus")));
	lead.setPcategory(readobj.getdata(rcount, readobj.getCell("pcategory")));
	lead.setEntrypage(readobj.getdata(rcount, readobj.getCell("entrypage")));
	lead.setSitemodule(readobj.getdata(rcount, readobj.getCell("sitemodule")));
	/*
	firstname.add(readobj.getdata(rcount,readobj.getCell("ptraining")));
	
	lastname.add(readobj.getdata(rcount, readobj.getCell("lastname")));
	
	email.add(readobj.getdata(rcount, readobj.getCell("email")));
	
	leadsource.add(readobj.getdata(rcount, readobj.getCell("leadsource")));
	
	country.add(readobj.getdata(rcount, readobj.getCell("country")));
	
	ptraining.add(readobj.getdata(rcount, readobj.getCell("ptraining")));
	
	//leadsource.add(readtestdata.getdata(rcount, readtestdata.getCell("website")));
	*/
	
	}
	leadlist.add(lead);
}	
	return leadlist;		
	
}

		
	
	
	
}



	
	

