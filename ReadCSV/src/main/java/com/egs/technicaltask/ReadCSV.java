package com.egs.technicaltask;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

public class ReadCSV {
   public static void main(String [] a) {
   Properties properties = new Properties();
   try {
		String csvPath = "";
		properties.load(ReadCSV.class.getResourceAsStream("/egs.properties"));
		csvPath = properties.getProperty("csvfilepath");
		if (StringUtils.isNotBlank(csvPath) ) {
			JSONArray allCustomers = readCsvFileAndCreateCustomers(csvPath);
			String apiEndPoint = properties.getProperty("endpoint");
			if (StringUtils.isNotBlank(apiEndPoint) && !allCustomers.isEmpty()) {
				for (int i=0;i<allCustomers.length();i++) {
					JSONObject customerJson  = allCustomers.getJSONObject(i);
					if (callService(customerJson,apiEndPoint)){
		         	   System.out.println("customer created successfully : " + customerJson.getLong("customerref"));
		            }else {
		         	   System.out.println("Could not Create customer : " + customerJson.getLong("customerref"));
		            }	
				}
			}
		}
		
	} catch (IOException | CsvException e) {
		System.out.println("Exception : " + e.getMessage());
	}
	   
   }
   public static JSONArray readCsvFileAndCreateCustomers(String filePath) throws IOException, CsvException {
	   JSONArray jarr = new JSONArray();
	   try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
           List<String[]> rows = reader.readAll();
           for (int i = 1; i < rows.size(); i++) { // Start from index 1 to skip headers
        	   JSONObject customerJson = new JSONObject();
        	   
        	   String[] rowData = rows.get(i);
               
               customerJson.put("customerref", Long.valueOf(rowData[0]));
               customerJson.put("customername",rowData[1] );
               
               customerJson.put("addressline1",rowData[2]);
               customerJson.put("addressline2",rowData[3]);
               
               customerJson.put("town",rowData[4]);
               customerJson.put("county",rowData[5]);
               customerJson.put("country",rowData[6]);
               customerJson.put("postcode",rowData[7]);
               
               jarr.put(customerJson);
           }
       }
       return jarr;
   }
   private static boolean callService(JSONObject jobj,String apiEndPoint) {
	   try {
		URL url = new URL (apiEndPoint);
		HttpURLConnection con = ((HttpURLConnection)url.openConnection());
		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Type", "application/json");
		con.setRequestProperty("Accept", "application/json");
		con.setDoOutput(true);
		String requestBody = jobj.toString();
		try(OutputStream os = con.getOutputStream()) {
		    byte[] input = requestBody.getBytes("utf-8");
		    os.write(input, 0, input.length);			
		}
		int status = con.getResponseCode();
		if (status ==201) {
			return true;
		}
	} catch (MalformedURLException e) {
		System.out.println("MalformedURLException" + e.getMessage());
		
	} catch (ProtocolException e) {
		System.out.println("ProtocolException" + e.getMessage());
	} catch (IOException e) {
		System.out.println("IOException" + e.getMessage());
	} 
	return false;  
	   
	   
   
   }
}
