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
import com.pigihi.service.admin.AdminCustomerAddressService;
import com.pigihi.service.admin.AdminCustomerNameService;
import com.pigihi.service.admin.AdminCustomerProfileImageService;

/**
 * @author Ashish Sam T George
 *
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/user/customer/admin")
public class AdminCustomerController {
	
	@Autowired
	private AdminCustomerQueryService adminCustomerQueryService;
	
	@Autowired
	private AdminCustomerStatusService adminCustomerStatusService;
	
	@Autowired
	private AdminCustomerNameService adminCustomerNameService;
	
	@Autowired
	private AdminCustomerProfileImageService adminCustomerProfileImageService;

	@Autowired
	private AdminCustomerAddressService adminCustomerAddressService;
		
	@Autowired
	private DataConverter dataConverter;

	@GetMapping("/all")
	public String getCustomers() {
		List<CustomerEntity> customers = adminCustomerQueryService.findAllCustomers();
		String customersJson = dataConverter.convertToJson(customers);
		return customersJson;
	}
		
	@GetMapping
	public String getCustomerInfo(@RequestParam String email) {
		CustomerEntity customer = adminCustomerQueryService.find(email);
		String customerJson = dataConverter.convertToJson(customer);
		return customerJson;
	}
	
	@DeleteMapping
	public String disableCustomerByAdmin(@RequestParam String email) throws IOException, InterruptedException {
		CustomerEntity adminDisabledCustomer = adminCustomerStatusService.disableCustomerByAdmin(email);
		String customerJson = dataConverter.convertToJson(adminDisabledCustomer);
		return customerJson;
	}

	@PatchMapping
	public String enableCustomerByAdmin(@RequestParam String email) throws IOException, InterruptedException {
		CustomerEntity adminEnabledCustomer = adminCustomerStatusService.enableCustomerByAdmin(email);
		String customerJson = dataConverter.convertToJson(adminEnabledCustomer);
		return customerJson;
	}
	
	@PutMapping("/fullName")
	public String changeCustomerName(@RequestParam String email,
										@RequestParam String fullName) throws IOException, InterruptedException {
		CustomerEntity changedCustomer = adminCustomerNameService.changeFullName(email, fullName);
		String customerJson = dataConverter.convertToJson(changedCustomer);
		return customerJson;
	}
	
	@PutMapping("/profileImage")
	public String changeCustomerProfileImage(@RequestParam String email,
												@RequestParam String imageUrl) {
		CustomerEntity changedCustomer = adminCustomerProfileImageService.changeProfileImage(email, imageUrl);
		String customerJson = dataConverter.convertToJson(changedCustomer);
		return customerJson;
	}
	
	@PutMapping("/address")
	public String changeCustomerAddress(@RequestParam String email,
											@RequestBody CustomerAddressModel customerAddressModel) {
		CustomerEntity changedCustomer = adminCustomerAddressService.changeAddress(email, customerAddressModel);
		String customerJson = dataConverter.convertToJson(changedCustomer);
		return customerJson;
	}

}
