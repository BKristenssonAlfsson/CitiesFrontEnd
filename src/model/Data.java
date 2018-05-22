package model;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;


public class Data {
	
	private final String  url = "http://localhost:8080/Project/cities/city";
	private String urlSpecificCity = "http://localhost:8080/Project/cities/city/";
	private Map<String, Integer> map = new HashMap<>();
	
	public Map<String, Integer> getSpecificCity(String string) {
		map.clear();
		String temp = null;
		try {
			temp = getText(urlSpecificCity + string);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String[] tmp = temp.split(" ");
		tmp[7] = tmp[7].substring(0, tmp[7].length() -1);
		
		map.put(tmp[2], Integer.parseInt(tmp[7]));
		return map;
	}
	
	public Map<String, Integer> getCities() {
		map.clear();
		JSONArray jsonArray = null;
		
		try {
			jsonArray = new JSONArray(getText(url));
		} catch (Exception e) {
			e.printStackTrace();
		}
        for(int i=0;i<jsonArray.length();i++){
        	JSONObject obj=jsonArray.getJSONObject(i); 
            String city=obj.getString("cityName"); 
            int population=obj.getInt("population"); 
            map.put(city, population);
        }
        return map;
	}
	
	public String getText(String url) throws Exception {
        URL website = new URL(url);
        URLConnection connection = website.openConnection();
        BufferedReader in = new BufferedReader( new InputStreamReader(connection.getInputStream(),"UTF8"));

        StringBuilder response = new StringBuilder();
        String inputLine;

        while ((inputLine = in.readLine()) != null) 
            response.append(inputLine);

        in.close();

        return response.toString();
    }

	public void deleteCity(String string) {
		try {
			URL address = new URL(urlSpecificCity + string);
			HttpURLConnection httpCon = (HttpURLConnection) address.openConnection();
			httpCon.setRequestMethod("DELETE");
			httpCon.setRequestProperty("custom-Header", "XYZ");
			httpCon.setRequestProperty("Content-Type", "application/JSON");
			httpCon.setDoOutput(true);
			httpCon.setDoInput(true);
			httpCon.setUseCaches(false);
			httpCon.getInputStream();
		} catch ( Exception e ) {
			e.printStackTrace();
		}
	}

	public String addCity(String parameters) {
		
		HttpURLConnection connection = null;
		URL target;
		try {
			target = new URL(url);
			connection = (HttpURLConnection) target.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/JSON");
			connection.setRequestProperty("custom-Header", "XYZ");
			connection.setRequestProperty("Content-Length", "" + Integer.toString(parameters.getBytes().length));
			connection.setUseCaches(false);
			connection.setDoInput(true);
			connection.setDoOutput(true);
			
			DataOutputStream wr = new DataOutputStream (connection.getOutputStream());
			wr.writeBytes(parameters);
			wr.flush();
			wr.close();
			
			InputStream is = connection.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String line;
			StringBuffer response = new StringBuffer();
			
			while((line = br.readLine()) != null ) {
				response.append(line);
				response.append("\r");
			}
			br.close();
			return response.toString();
			
		} catch (Exception e ) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void updateCity(String name, String newName, String population) {
		try {
			URL url = new URL("http://localhost:8080/Project/cities/city/?name="+ name +"&newName="+ newName + "&population=" + population);
			HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
			httpCon.setDoOutput(true);
			httpCon.setRequestMethod("PUT");
			OutputStreamWriter out = new OutputStreamWriter(
			httpCon.getOutputStream());
			out.write("Resource content");
			out.close();
			httpCon.getInputStream();
		} catch ( Exception e ) {
			
		}

	}

	public Map<String, Integer> getSpecificCity(String string, String string2) {
		map.clear();
		JSONArray jsonArray = null;
		try {
			jsonArray = new JSONArray(getText(urlSpecificCity + "MinMax/?cityOne="+ string +"&cityTwo="+string2));
		} catch (Exception e) {
			e.printStackTrace();
		}
        for(int i=0;i<jsonArray.length();i++){
        	JSONObject obj=jsonArray.getJSONObject(i); 
            String city=obj.getString("cityName"); 
            int population=obj.getInt("population"); 
            map.put(city, population);
        }
        return map;
	}

}
