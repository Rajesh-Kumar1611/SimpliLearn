package simpli.kvpsalesforceapi.org;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class WebServiceHelper {
	public static void main(String[] args) {

		try {
			String access_token =getAccessToken();
			String raw_body= " [{\"company\":\"\",\"country\":\"India\",\"description\":\"This is a test data\",\"email\":\"rahul12@fake.com\",\"firstName\":\"Test\",\"address\":\"Text\",\"city\":\"Gurgaon\",\"lastname\":\"Rajesh\",\"leadsource\":\"Chat\",\"phone\":\"+91-8904623622\",\"postalcode\":\"123\",\"title\":\"\",\"action_taken_most_recent\":\"\",\"action_Taken_Email_Phrase\":\"\",\"Browser\":\"\",\"Entry_Page\":\"http://www.simplilearn.com/project-bsrrter-and-its-importance-article|Expert Webinar: Why PMP|Thu, Aug 11, 2016 9:00 PM IST\",\"Lead_Type\":\"\",\"Operating_System\":\"\",\"Site_Module\":\"\",\"Topic_Interest\":\"\",\"URL_to_Click\":\", \",\"UTM_Campaign_First\":\"Text\",\"UTM_Campaign_Last\":\"Text\",\"UTM_First_Source\":\"Text\",\"UTM_Medium\":\"Text\",\"UTM_Source\":\"Text\",\"Website\":\"http://www.simplilearn.com/\",\"country_id\":\"101\",\"city_id\":\"201\",\"lead_creation_mode\":\"Text\",\"resource_tag_id\":\"301\",\"lead_stage\":\"New\",\"lead_status\":\"New\",\"product_type_id\":\"Text\",\"product_type_name\":\"\",\"product_id \":\"\",\"product_name\":\"Microsoft<sup>®</sup> Office 2013 Word::#::All-in-one Microsoft<sup>®</sup> Office 2010 Suite\",\"billing_type_id\":\"2\",\"billing_type_name\":\"\",\"category\":\"\",\"trainingType\":\"Text\",\"course_category_name\":\"\",\"queryType\":\"\",\"lead_ip\":\"\",\"first_cookie_source\":\"\",\"last_cookie_source\":\"Text\",\"device_name\":\"Text\",\"device_type\":\"Text\",\"querySourceName\":\"Text\",\"lead_entry_source\":\"Text\",\"queryCategory\":\"Text\",\"alternateEmail\":\"abcxyz@fake.com\",\"workshop\":\"2\",\"dates\":\"33\",\"lead_id\":\"\",\"alternatePhone\":\"3434\",\"queryType_name\":\"B2C\",\"stage_name\":\"New\",\"status_name\":\"New\"}]";
			String response = doPostRequest(access_token, "https://cs6.salesforce.com/services/apexrest/LeadAPI", null, raw_body);
			System.out.println(response);
		} catch (KeyManagementException	| NoSuchAlgorithmException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static String doPostRequest(String access_token,String URL,Map<String,String> request_params,String raw_body) throws ClientProtocolException, IOException
	{
		
			System.out.println("Raw Body"+raw_body);
		    @SuppressWarnings({ "resource", "deprecation" })
	        HttpClient httpclient = new DefaultHttpClient();
	        HttpPost post = new HttpPost(URL);
	        post.addHeader("Authorization","OAuth "+access_token);
	        post.addHeader("Content-Type","application/json");
	        if(request_params != null)
	        {
	        	
	        	List<NameValuePair> params = new ArrayList<NameValuePair>();
		        for(String header:request_params.keySet())
		        {
		        	params.add(new BasicNameValuePair(header,request_params.get(header)));	
		        }
		        post.setEntity(new UrlEncodedFormEntity(params));
	        }
	        
	        if(raw_body !=null)
	        {
	        	
	        	HttpEntity http_entity = new ByteArrayEntity(raw_body.getBytes());
	        	post.setEntity(http_entity);
	        }
	        
	        HttpResponse response = httpclient.execute(post);
	        String body = EntityUtils.toString(response.getEntity());
	        return body;	
	}
	
	
    public static String getAccessToken() throws ClientProtocolException, IOException, NoSuchAlgorithmException, KeyManagementException {
    	
        @SuppressWarnings({ "resource", "deprecation" })
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost post = new HttpPost("https://test.salesforce.com/services/oauth2/token");
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("client_id", "3MVG9e2mBbZnmM6kKC1J8H07eGs4PI99bVoJRaPA6qlq0CHtBF6uubwtdq9KysLmQD0zv_Gtz1aeKLwcm6m8z"));
        params.add(new BasicNameValuePair("client_secret", "4331865267158042957"));
        params.add(new BasicNameValuePair("grant_type", "password"));
        params.add(new BasicNameValuePair("username", "tushar.bansal@simplilearn.com.newqa"));
        params.add(new BasicNameValuePair("password", "Simpli@123WHJcgOeUD4MoHuRZu9Wlu4Qzo"));
        post.setEntity(new UrlEncodedFormEntity(params));
        HttpResponse response = httpclient.execute(post);
        String body = EntityUtils.toString(response.getEntity());
        if(body!= null && body.contains("access_token"))
        {
        JSONObject obj = new JSONObject(body);
        return obj.getString("access_token");
        }else
        {
        	return null;
        }
    }

}
