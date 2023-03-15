/**
 * 
 */
package com.pigihi.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.pigihi.entity.CustomerEntity;
import com.pigihi.entity.StatusEnum;
import com.pigihi.model.CustomerAddressModel;
import com.pigihi.model.EditCustomerModel;
import com.pigihi.repository.CustomerRepository;
import com.pigihi.service.DELETERequestSender;
import com.pigihi.service.PATCHRequestSender;

/**
 * @author Ashish Sam T George
 *
 */
@Service
public class CustomerEditService {
	
	@Autowired
	private PUTRequestSender putRequestSender;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Value("${authService.uri}")
	private String authUri;
	
	@Value("${authService.endpoint.profileImage.endpoint}")
	private String profileImageEndpoint;
	
	@Value("${authService.endpoint.profileImage.queryParam}")
	private String profileImageQueryParam;
	
	@Value("${authService.endpoint.address.endpoint}")
	private String addressEndpoint;

	/**
	 * Edit details of already existing customer
	 * 
	 * @param editCustomerModel
	 * @return CustomerEntity
	 * 
	 * @see CustomerEntity
	 * 
	 * @author Ashish Sam T George
	 * 
	 */
	public CustomerEntity editCustomer(EditCustomerModel editCustomerModel) {
		//TODO How about making the neccessary changes in authentication service
		
		CustomerEntity customer = customerRepository.findByEmail(editCustomerModel.getEmail());
		customer.setMobile(editCustomerModel.getMobile());
		customer.setImageUrl(editCustomerModel.getImageUrl());
		customer.setHouseName(editCustomerModel.getHouseName());
		customer.setStreetName(editCustomerModel.getStreetName());
		customer.setCityName(editCustomerModel.getCityName());
		customer.setPincode(editCustomerModel.getPincode());
		
		customerRepository.save(customer);
		return customer;
		
	}

}
