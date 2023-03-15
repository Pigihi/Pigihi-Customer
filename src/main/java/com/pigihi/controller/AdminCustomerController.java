/**
 * 
 */
package com.pigihi.controller;


import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.pigihi.entity.CustomerEntity;
import com.pigihi.library.dataConverter.service.DataConverter;
import com.pigihi.model.CustomerAddressModel;
import com.pigihi.service.CustomerAddressService;
import com.pigihi.service.CustomerNameService;
import com.pigihi.service.CustomerProfileImageService;
import com.pigihi.service.admin.AdminCustomerQueryService;
import com.pigihi.service.admin.AdminCustomerStatusService;

/**
 * @author Ashish Sam T George
 *
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/user/customer/admin")
public class AdminCustomerController {
	
	@Autowired
	private AdminCustomerQueryService customerAdminQueryService;
	
	@Autowired
	private AdminCustomerStatusService customerAdminService;
	
	@Autowired
	private CustomerNameService customerNameService;
	
	@Autowired
	private CustomerProfileImageService customerProfileImageService;
	
	@Autowired
	private CustomerAddressService customerAddressService;
	
	@Autowired
	private DataConverter dataConverter;

	// TODO Currently, a seperate controller method is used to handle disabling
	// customer by admin. Revaluate whether this is neccessary
	@DeleteMapping
	public String disableCustomerByAdmin(@RequestParam String email) throws IOException, InterruptedException {

		CustomerEntity adminDisabledCustomer = customerAdminService.disableCustomerByAdmin(email);
		String customerJson = dataConverter.convertToJson(adminDisabledCustomer);
		return customerJson;
		
	}

	// TODO Only admins should be able to do this
	@PatchMapping
	public String enableCustomerByAdmin(@RequestParam String email) throws IOException, InterruptedException {
		CustomerEntity adminEnabledCustomer = customerAdminService.enableCustomerByAdmin(email);
		String customerJson = dataConverter.convertToJson(adminEnabledCustomer);
		return customerJson;
	}

	// TODO Only admins should be able to do this
	@GetMapping("/all")
	public String getCustomers() {
		List<CustomerEntity> customers = customerAdminQueryService.findAllCustomers();
//		Gson listGson = new Gson();
//		String customersJson = listGson.toJson(customers);
		String customersJson = dataConverter.convertToJson(customers);
		return customersJson;
	}
	
	@PutMapping("/fullName")
	public String changeCustomerName(@RequestParam String email,
										@RequestParam String fullName) throws IOException, InterruptedException {
		CustomerEntity changedCustomer = customerNameService.changeFullName(email, fullName);
		String customerJson = dataConverter.convertToJson(changedCustomer);
		return customerJson;
	}
	
	@PutMapping("/profileImage")
	public String changeCustomerProfileImage(@RequestParam String email,
												@RequestParam String imageUrl) {
		CustomerEntity changedCustomer = customerProfileImageService.changeProfileImage(email, imageUrl);
		String customerJson = dataConverter.convertToJson(changedCustomer);
		return customerJson;
	}
	
	@PutMapping("/address")
	public String changeCustomerAddress(@RequestParam String email,
											@RequestBody CustomerAddressModel customerAddressModel) {
		CustomerEntity changedCustomer = customerAddressService.changeAddress(email, customerAddressModel);
		String customerJson = dataConverter.convertToJson(changedCustomer);
		return customerJson;
	}

}
