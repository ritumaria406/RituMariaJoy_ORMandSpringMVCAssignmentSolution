package com.gl.crmapp.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.gl.crmapp.entity.Customer;

@Service
public interface CustomerService {
	
	public List<Customer> findAll();	
		
	public Customer findById(int id);
	
	public void save(Customer cust); // save or update
	
	public void deleteById(int id);

	
}
