package com.gl.crmapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gl.crmapp.service.CustomerService;

import com.gl.crmapp.entity.Customer;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	
	@RequestMapping("/list")
	public String listBooks(Model theModel) {
		
		List<Customer> customer = customerService.findAll();
		theModel.addAttribute("customers",customer);
		return "list-customer"; // /WEB-INF/views/list-books.jsp
		
	}
	
	@RequestMapping("/showFormForAdd")
	public String showFormforAdd(Model theModel) {
		Customer theCustomer = new Customer();
		
		theModel.addAttribute("Customer",theCustomer);
		
		return "Customer-form";
		
	}
	
	@RequestMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("id") int id,Model theModel) {
		Customer theCustomer = customerService.findById(id);
		
		theModel.addAttribute("Customer",theCustomer);
		
		return "Customer-form";
		
	}
	
	
	
	@PostMapping("/save")
	public String save(@RequestParam("id") int id,
			@RequestParam("firstName") String firstName,@RequestParam("lastName") String lastName,@RequestParam("emailId") String emailId
			) {
	
		System.out.println(id);
		
		Customer theCustomer;
	
	if(id!=0) {
		theCustomer  = customerService.findById(id);
		theCustomer.setFirstName(firstName);
		theCustomer.setLastName(lastName);
		theCustomer.setEmailId(emailId);
			
	}
	else
		theCustomer = new Customer(firstName,lastName,emailId);
	customerService.save(theCustomer);
	
	return "redirect:/customer/list";
	
}
	
	
	@RequestMapping("/delete")
	public String delete(@RequestParam("id") int theId) {

		// delete the Customer
		customerService.deleteById(theId);

		
		return "redirect:/customer/list";

	}

}
