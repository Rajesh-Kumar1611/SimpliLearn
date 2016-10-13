package simpli.kvpsalesforceapi.org;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class WebServiceHelper {
	public static void main(String[] args) {

		try {
			System.out.println(getAccessToken());
		} catch (KeyManagementException	| NoSuchAlgorithmException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static String doPostRequest(String access_token,String URL,Map<String,String> request_params) throws ClientProtocolException, IOException
	{
		    @SuppressWarnings({ "resource", "deprecation" })
	        HttpClient httpclient = new DefaultHttpClient();
	        HttpPost post = new HttpPost(URL);
	        post.addHeader("Authorization","OAuth "+access_token);
	        if(request_params != null)
	        {
	        	
	        	List<NameValuePair> params = new ArrayList<NameValuePair>();
		        for(String header:request_params.keySet())
		        {
		        	params.add(new BasicNameValuePair(header,request_params.get(header)));	
		        }
		        post.setEntity(new UrlEncodedFormEntity(params));
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
