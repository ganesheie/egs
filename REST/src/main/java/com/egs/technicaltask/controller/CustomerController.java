package com.egs.technicaltask.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.egs.technicaltask.model.Customer;
import com.egs.technicaltask.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;



@RestController
public class CustomerController {
	
	@Autowired
	CustomerService customerService; 
	
	@PostMapping(value ={"/createcustomer/","/createcustomer"})
	public ResponseEntity<String> createCustomer (@RequestBody String customerJson) {
        
       JSONObject jobj = new JSONObject(customerJson);
       Customer customer = new Customer();
       customer.setCustomerref(jobj.getLong("customerref"));
       customer.setCustomername(jobj.getString("customername"));
       
       customer.setAddressline1(jobj.getString("addressline1"));
       customer.setAddressline2(jobj.getString("addressline2"));
       
       customer.setTown(jobj.getString("town"));
       customer.setCountry(jobj.getString("country"));
       customer.setCounty(jobj.getString("county"));
       customer.setPostcode(jobj.getString("postcode"));
       
        customerService.AddCustomer(customer);
		
	    return new ResponseEntity<String>("Customer Created Successfully", HttpStatus.CREATED);
    }

	@GetMapping(value= {"/getcustomer/{customerref}/","/getcustomer/{customerref}"})	
	public ResponseEntity getCustomerByRef (@PathVariable String customerref) {
		Customer customer = customerService.getCustomer(Long.valueOf(customerref));
		if (customer!=null) { 
			return new ResponseEntity<Customer >(customer,HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("User Not Found",HttpStatus.NOT_FOUND);
		}
	}
		
	
}
