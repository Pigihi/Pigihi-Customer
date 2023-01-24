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

import com.pigihi.entity.CustomerEntity;
import com.pigihi.model.EditCustomerModel;
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
	
	@GetMapping
	public String customerInfo(@RequestParam String email) {
		
		
		
	}
	
	@PostMapping("/add")
	public String addCustomer(@RequestBody CustomerEntity customerEntity) {
		
		
		
	}
	
	@PutMapping("/edit")
	public String editCustomer(@RequestBody EditCustomerModel editCustomerModel) {
		
	}
	
	@DeleteMapping("/disable")
	public CustomerEntity disableCustomer(@RequestParam String email) {
		
	}
	
	@PutMapping("/enable")
	public CustomerEntity enableCustomer(@RequestParam String email) {
		
	}

}
