import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Formatter;
import javax.net.ssl.HttpsURLConnection;

public class HTTP {
 
	private final String USER_AGENT = "Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:22.0) Gecko/20100101 Firefox/22.0";
 
	public static void main(String[] args) throws Exception {
 
		HTTP http = new HTTP();
		String data = http.http_req("http://nextmicrosoftceo.com/home/candidate/746d3602-0b8d-49bd-b2d7-c238409a40c9");
 		http.http_post(http.getToken(data),http.getRMS_ID(data));
 
 
	}
 
	// HTTP GET request
	private String http_req(String url) throws Exception {
	 
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
 
		// optional default is GET
		con.setRequestMethod("GET");
 
		//add request header
		con.setRequestProperty("User-Agent", USER_AGENT);
 
		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);
 
		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
 
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		
		return response.toString();
		
	}
	//dunno if this even works in the slightest
	private String http_post(String token, String rms_id) throws Exception {
 		String url = "http://nextmicrosoftceo.com/";
		URL obj = new URL(url);
		HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
 
		//add reuqest header
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
 
		String urlParameters = "__RequestVerificationToken="+token+"&id="+rmd_id;
 
		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();
 
		int responseCode = con.getResponseCode();
		//debugging ftw
		System.out.println("Response Code : " + responseCode);
 
		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
 
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
 

		return response.toString();
 
	}	
	
	String getRMS_ID(String input)
	{
		String rms_id;
		
		rms_id = input.split("<input type=\"hidden\" value=\"")[1].split("\"")[0];
		
		return rms_id;
	}
	
	String getToken(String input)
	{
		String token;
		
		token = input.split("__RequestVerificationToken")[1].split("value=\"")[1].split('"')[0];
		
		return token;
	}
	
	String[] getMuhCookies(URLConnection niggerzits)
	{
		ArrayList<String> Nigs = new ArrayList<String>();
		String current;
		
		for(int i = 1; (current = niggerzits.getHeaderFieldKey(i)) != null; i++)
		{
			if(current.equals("Set-Cookie"))
			{
				Nigs.add(niggerzits.getHeaderField(i));
			}
		}
		
		return Nigs.toArray(new String[Nigs.size()]);
	}
}
 
