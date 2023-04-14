/**
 * 
 */
package com.pigihi.controller;

import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.pigihi.entity.CustomerEntity;
import com.pigihi.library.dataConverter.service.DataConverter;
import com.pigihi.model.CustomerAddressModel;
import com.pigihi.model.CustomerPasswordModel;
import com.pigihi.model.EditCustomerModel;
import com.pigihi.service.CustomerAddService;
import com.pigihi.service.CustomerAddressService;
import com.pigihi.service.CustomerNameService;
import com.pigihi.service.CustomerProfileImageService;
import com.pigihi.service.CustomerQueryService;
import com.pigihi.service.CustomerStatusService;

import jakarta.servlet.http.HttpServletRequest;

/**
 * Controller class for customer API requests
 * 
 * @author Ashish Sam T George
 *
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/user/customer/self")
public class CustomerController {
	
	@Autowired
	private CustomerQueryService customerQueryService;
	
	@Autowired
	private CustomerAddService customerAddService;
	
	@Autowired
	private CustomerStatusService customerStatusService;
	
	@Autowired
	private CustomerNameService customerNameService;
	
	@Autowired
	private CustomerProfileImageService customerProfileImageService;
	
	@Autowired
	private CustomerAddressService customerAddressService;
		
	@Autowired
	private DataConverter dataConverter;
	
//	@Value("${authService.endpoint.addUser.endpoint}")
//	private String addAuthUserEndpoint;
	
	//TODO Should sanitize all data input
		
	/**
	 * Handle request to get details of customer
	 * 
	 * @param email
	 * @return JSON string
	 * 
	 * @see CustomerEntity
	 * 
	 * @author root
	 * 
	 */
	@GetMapping
	public String customerInfo(HttpServletRequest request) {
		Authentication authenticatedUser = SecurityContextHolder.getContext().getAuthentication();
		String email = (String) authenticatedUser.getPrincipal();
		
		CustomerEntity customerEntity = customerQueryService.customerInfo(email);
		String customerJson = dataConverter.convertToJson(customerEntity);
		return customerJson;	
	}
	
	/**
	 * Handle request to add new customer
	 * 
	 * @param customerEntity
	 * @return JSON string
	 * 
	 * @see CustomerEntity
	 * 
	 * @author Ashish Sam T George
	 * 
	 */
	@PostMapping
	public String addCustomer(@RequestBody CustomerEntity customerEntity) {
		CustomerEntity savedCustomerEntity = customerAddService.saveCustomer(customerEntity);
		String customerJson = dataConverter.convertToJson(savedCustomerEntity);
		return customerJson;
	}
	
	/**
	 * Handle request to disable an already existing customer
	 * 
	 * @param email
	 * @return JSON string
	 * 
	 * @see CustomerEntity
	 * 
	 * @author Ashish Sam T George
	 * 
	 */
	@DeleteMapping
	public String disableCustomer() throws IOException, InterruptedException {
		Authentication authenticatedUser = SecurityContextHolder.getContext().getAuthentication();
		String email = (String) authenticatedUser.getPrincipal();
		
		CustomerEntity disabledCustomer = customerStatusService.disableCustomer(email);
		String customerJson = dataConverter.convertToJson(disabledCustomer);
		return customerJson;
	}
	
	/**
	 * Handle request to enable an already existing customer
	 * 
	 * @param email
	 * @return JSON string
	 * 
	 * @see CustomerEntity
	 * 
	 * @author Ashish Sam T George
	 * 
	 */
	@PatchMapping
	public String enableCustomer() throws InterruptedException, IOException {
		Authentication authenticatedUser = SecurityContextHolder.getContext().getAuthentication();
		String email = (String) authenticatedUser.getPrincipal();
		
		CustomerEntity enabledCustomer = customerStatusService.enableCustomer(email);
		String customerJson = dataConverter.convertToJson(enabledCustomer);
		return customerJson;
	}
	
	@PutMapping("/fullName")
	public String changeFullName(@RequestParam String fullName) throws InterruptedException, IOException {
		Authentication authenticatedUser = SecurityContextHolder.getContext().getAuthentication();
		String email = (String) authenticatedUser.getPrincipal();
		
		//TODO Call authentication service to modify full name
		CustomerEntity changedCustomer = customerNameService.changeFullName(email, fullName);
		String customerJson = dataConverter.convertToJson(changedCustomer);
		return customerJson;
	}
	
	@PutMapping("/profileImage")
	public String changeProfileImage(@RequestParam String imageUrl) {
		Authentication authenticatedUser = SecurityContextHolder.getContext().getAuthentication();
		String email = (String) authenticatedUser.getPrincipal();
		
		CustomerEntity changedCustomer = customerProfileImageService.changeProfileImage(email, imageUrl);
		String customerJson = dataConverter.convertToJson(changedCustomer);
		return customerJson;
	}
	
	@PutMapping("/address")
	public String changeAddress(@RequestBody CustomerAddressModel customerAddressModel) {
		Authentication authenticatedUser = SecurityContextHolder.getContext().getAuthentication();
		String email = (String) authenticatedUser.getPrincipal();
		
		CustomerEntity changedCustomer = customerAddressService.changeAddress(email, customerAddressModel);
		String customerJson = dataConverter.convertToJson(changedCustomer);
		return customerJson;
	}
	
	@PutMapping("/email")
	public String changeEmail(@RequestParam String email) {
		//TODO Get current email from header
		
		//TODO Do verification of new email
		//TODO Call authentication service to modify email
		return "NOT IMPLEMENTED";
	}
	
	@PutMapping("/mobile")
	public String changeMobile(@RequestParam String mobile) {
		//TODO Get email from header
		
		//TODO Do verification of new mobile
		//TODO Call authentication service to modify mobile
		return "NOT IMPLEMENTED";
	}
	
	@PutMapping("/password")
	public String changePassword(@RequestBody CustomerPasswordModel customerPasswordModel) {
		//TODO Get email from header
		
		//TODO Check whether old password is correct
		//TODO Check new password and confirm password are same
		//TODO Both of the above logic should be in authentication service
		//TODO Call authentication service
		return "NOT IMPLEMENTED";
	}

}
