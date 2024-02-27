package com.egs.technicaltask;
import com.egs.technicaltask.model.Customer;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadCSV {
   public static void main(String [] a) {
	   String csvPath = "D:\\test_csv.csv";
	   try {
		System.out.println(readCsvFile(csvPath));
	} catch (IOException | CsvException e) {
	}
	   
   }
   public static List<Customer> readCsvFile(String filePath) throws IOException, CsvException {
       try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
           List<String[]> rows = reader.readAll();
           // Assuming the first row contains headers and Customer class represents your data structure
           List<Customer> customers = new ArrayList<>();
           for (int i = 1; i < rows.size(); i++) { // Start from index 1 to skip headers
               String[] rowData = rows.get(i);
               Customer customer = new Customer();
               customer.setCustomerRef(rowData[0]);
               customer.setCustomerName(rowData[1]);
               customer.setAddressLine1(rowData[2]);
               customer.setAddressLine2(rowData[3]);
               customer.setTown(rowData[4]);
               customer.setCounty(rowData[5]);
               customer.setCountry(rowData[6]);
               customer.setPostcode(rowData[7]);
               customers.add(customer);
           }
           return customers;
       }
   }
}
