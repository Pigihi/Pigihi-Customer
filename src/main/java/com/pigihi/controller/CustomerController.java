/**
 * 
 */
package com.pigihi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.pigihi.entity.CustomerEntity;
import com.pigihi.model.EditCustomerModel;
import com.pigihi.service.CustomerServiceInterface;
import com.pigihi.service.QueryServiceInterface;

import jakarta.servlet.http.HttpServletRequest;

/**
 * @author Ashish Sam T George
 *
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/user/customer")
public class CustomerController {
	
	@Autowired
	private QueryServiceInterface customerQueryService;
	
	@Autowired
	private CustomerServiceInterface customerService;
	
	@GetMapping
	public String customerInfo(@RequestParam String email) {
		
		CustomerEntity customerEntity = customerQueryService.customerInfo(email);
		String customer = ConvertToJson(customerEntity);
		return customer;
		
	}
	
	@PostMapping("/add")
	public String addCustomer(@RequestBody CustomerEntity customerEntity) {
		
		CustomerEntity savedCustomerEntity = customerService.saveCustomer(customerEntity);
		String customer = ConvertToJson(savedCustomerEntity);
		return customer;
		
	}
	
	@PutMapping("/edit")
	public String editCustomer(@RequestBody EditCustomerModel editCustomerModel) {
		
		CustomerEntity editedCustomer = customerService.editCustomer(editCustomerModel);
		String customer = ConvertToJson(editedCustomer);
		return customer;
		
	}
	
	@DeleteMapping("/disable")
	public String disableCustomer(@RequestParam String email) {
		
		CustomerEntity disabledCustomer = customerService.disableCustomer(email);
		String customer = ConvertToJson(disabledCustomer);
		return customer;
		
	}
	
	@PutMapping("/enable")
	public String enableCustomer(@RequestParam String email) {
		
		CustomerEntity enabledCustomer = customerService.enableCustomer(email);
		String customer = ConvertToJson(enabledCustomer);
		return customer;
		
	}
	
	private String ConvertToJson(CustomerEntity customerEntity) {
		Gson gson = new Gson();
		String customer = gson.toJson(customerEntity);
		return customer;
	}

}
