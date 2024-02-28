package com.egs.technicaltask.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egs.technicaltask.model.Customer;
import com.egs.technicaltask.repository.CustomerRespository;

@Service
public class CustomerService {
	@Autowired
	public CustomerRespository customerRepository;
	
	
	public Customer getCustomer(Long customerRef) {
		Optional<Customer> optionalEntity =  customerRepository.findById(customerRef);
		return optionalEntity.orElse(null);
	}
	
	public Customer AddCustomer(Customer customer) {
		 return customerRepository.saveAndFlush(customer);
	}
	
	
	
}
