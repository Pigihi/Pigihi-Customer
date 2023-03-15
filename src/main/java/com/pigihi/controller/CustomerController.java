/**
 * 
 */
package com.pigihi.controller;

import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.pigihi.service.CustomerEditService;
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
	private CustomerEditService customerEditService;
	
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
//	@GetMapping
//	public String customerInfo(@RequestParam String email) {
//		
//		CustomerEntity customerEntity = customerQueryService.customerInfo(email);
//		String customer = ConvertToJson(customerEntity);
//		return customer;
//		
//	}
	
	@GetMapping
	public String customerInfo(@RequestParam String email, HttpServletRequest request) {
		
		//TODO Get email from header
		System.out.println("Headers received in Customer Service: ");
		Enumeration<String> headers = request.getHeaderNames();
		while(headers.hasMoreElements()) {
			System.out.println(headers.nextElement());
		}
		
		CustomerEntity customerEntity = customerQueryService.customerInfo(email);
//		String customer = ConvertToJson(customerEntity);
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
//	@PostMapping("/add")
	@PostMapping
	public String addCustomer(@RequestBody CustomerEntity customerEntity) {
		
		CustomerEntity savedCustomerEntity = customerAddService.saveCustomer(customerEntity);
//		String customer = ConvertToJson(savedCustomerEntity);
		String customerJson = dataConverter.convertToJson(savedCustomerEntity);
		return customerJson;
		
	}
	
	/**
	 * Handle request to edit already existing customer
	 * 
	 * @param editCustomerModel
	 * @return JSON string
	 * 
	 * @see CustomerEntity
	 * @see EditCustomerModel
	 * 
	 * @author Ashish Sam T George
	 * 
	 */
//	@PutMapping("/edit")
	@PutMapping
	public String editCustomer(@RequestBody EditCustomerModel editCustomerModel) {
		
		//TODO Need to check whether customer already exists or not
		
		CustomerEntity editedCustomer = customerEditService.editCustomer(editCustomerModel);
//		String customer = ConvertToJson(editedCustomer);
		String customerJson = dataConverter.convertToJson(editedCustomer);
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
//	@DeleteMapping("/disable")
	@DeleteMapping
	public String disableCustomer(@RequestParam String email) throws IOException, InterruptedException {
		
		//TODO Get email from header
		CustomerEntity disabledCustomer = customerStatusService.disableCustomer(email);
//		String customer = ConvertToJson(disabledCustomer);
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
//	@PatchMapping("/enable")
	@PatchMapping
	public String enableCustomer(@RequestParam String email) throws InterruptedException, IOException {
		
		//TODO Get email from header
		CustomerEntity enabledCustomer = customerStatusService.enableCustomer(email);
//		String customer = ConvertToJson(enabledCustomer);
		String customerJson = dataConverter.convertToJson(enabledCustomer);
		return customerJson;
//		return customer;
		
	}
	
	@PutMapping("/fullName")
	public String changeFullName(@RequestParam String fullName) throws InterruptedException, IOException {
		//TODO Get email from header
		
		//TODO Call authentication service to modify full name
		String email = "";
		CustomerEntity changedCustomer = customerNameService.changeFullName(email, fullName);
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
	
	@PutMapping("/profileImage")
	public String changeProfileImage(@RequestParam String imageUrl) {
		//TODO Get email from header
		
		String email = "";
		CustomerEntity changedCustomer = customerProfileImageService.changeProfileImage(email, imageUrl);
		String customerJson = dataConverter.convertToJson(changedCustomer);
		return customerJson;
	}
	
	@PutMapping("/address")
	public String changeAddress(@RequestBody CustomerAddressModel customerAddressModel) {
		//TODO Get email from header
		
		String email = "";
		CustomerEntity changedCustomer = customerAddressService.changeAddress(email, customerAddressModel);
		String customerJson = dataConverter.convertToJson(changedCustomer);
		return customerJson;
	}

}
