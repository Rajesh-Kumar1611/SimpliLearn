package simpli.kvpsalesforceapi.org;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import com.sforce.soap.partner.Connector;
import com.sforce.soap.partner.Error;
import com.sforce.soap.partner.PartnerConnection;
import com.sforce.soap.partner.QueryResult;
import com.sforce.soap.partner.SaveResult;
import com.sforce.soap.partner.sobject.SObject;
import com.sforce.ws.ConnectionException;
import com.sforce.ws.ConnectorConfig;

import readtestdata.kvp.org.ReadData;
import readtestdata.kvp.org.StoreData;
import simplilearn.kvp.pojo.Lead;

public class SalesforceUtility {

	ArrayList<Lead> leaddata = new ArrayList<Lead>();
	static String user, password, token, uniqueid;
	static PartnerConnection connection = null;

	public static boolean getConnection() {
		boolean ret = false;
		/*
		 * SSLContext ctx = SSLContext.getInstance("TLSv1.2"); ctx.init(null,
		 * null, null); SSLContext.setDefault(ctx);
		 * 
		 */
		ConnectorConfig config = new ConnectorConfig();
		config.setAuthEndpoint("https://test.salesforce.com/services/Soap/u/18.0/");
		// config.setServiceEndpoint("https://test.salesforce.com/services/Soap/u/31.0/00Dp0000000D7eA");
		config.setUsername("tushar.bansal@simplilearn.com.newqa");
		config.setPassword("Simpli@123WHJcgOeUD4MoHuRZu9Wlu4Qzo");

		try {
			System.out.println(config.getRestEndpoint());
			connection = Connector.newConnection(config);
			ret = true;
		} catch (ConnectionException e) {
			System.out.println("Error while connecting" + e);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Failed connecting to Salesforce.com\n" + e, "Weighsoft Enterprise",
					JOptionPane.ERROR_MESSAGE);
		}
		return ret;
	}

	public static SObject[] getDetails(String query) {
		SObject[] value = null;
		try {
			QueryResult result = connection.query(query);
			value = result.getRecords();
			System.out.println("Total Records"+result.getSize());
		} catch (Exception e) {
			System.out.println("error" + e);
		}
		return value;
	}

	public static  void Insert(SObject[] sobjects)
	{
		try {
			SaveResult[] saveresult = connection.create(sobjects);
			for (SaveResult sr : saveresult) {
				if (sr.isSuccess()) {
					System.out.println(sr.getId());
				} else {
					for (Error e : sr.getErrors()) {
						System.out.println(e.getMessage());
					}
				}
			}

		} catch (ConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void InsertLead(String url, int sheetindex) {
		ReadData readobj = new ReadData();
		StoreData storeobj = new StoreData();
		readobj.getpath(url);
		readobj.getsheetindex(sheetindex);
		readobj.rowcount();
		readobj.getcolvalue();
		readobj.columndictionary();
		leaddata = storeobj.createlist();
		SObject[] leaddata1 = new SObject[leaddata.size()];
		int i = 0;
		for (Lead l : leaddata) {
			SObject leadrec = new SObject();
			leadrec.setType("Lead");
			leadrec.setField("RecordTypeid", "01228000000ltkv");
			leadrec.setField("LastName", l.getLastname());
			leadrec.setField("Email", l.getEmail());
			leadrec.setField("Status", l.getLeadstatus());
			leadrec.setField("LeadSource", l.getLeadsource());
			leadrec.setField("Country", l.getCountry());
			leadrec.setField("Website", l.getWebsite());
			leadrec.setField("Primary_Category_of_Interest__c", l.getPcategory());
			leadrec.setField("Entry_Page__c", l.getEntrypage());
			leadrec.setField("Site_Module__c", l.getSitemodule());
			leaddata1[i] = leadrec;
			i++;
		}
		// System.out.println(leaddataa[0]);
		try {
			SaveResult[] saveresult = connection.create(leaddata1);
			for (SaveResult sr : saveresult) {
				if (sr.isSuccess()) {
					System.out.println(sr.getId());
				} else {
					for (Error e : sr.getErrors()) {
						System.out.println(e.getMessage());
					}
				}
			}

		} catch (ConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * try { //connection.create(new SObject[]{leadrec}); }catch
	 * (ConnectionException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); }
	 */
}
