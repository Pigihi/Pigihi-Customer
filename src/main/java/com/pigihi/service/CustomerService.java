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
import org.springframework.stereotype.Service;

import com.pigihi.entity.CustomerEntity;
import com.pigihi.entity.StatusEnum;
import com.pigihi.model.EditCustomerModel;
import com.pigihi.repository.CustomerRepository;

/**
 * Implementation class for customer service interface
 * Contains methods that can change values
 * 
 * @author Ashish Sam T George
 *
 */
@Service
public class CustomerService implements CustomerServiceInterface {
	
	@Autowired
	private CustomerRepository customerRepository;

	/**
	 * Save customer details
	 * 
	 * @param customerEntity
	 * @return CustomerEntity
	 * 
	 * @see CustomerEntity
	 * 
	 * @author Ashish Sam T George
	 * 
	 */
	@Override
	public CustomerEntity saveCustomer(CustomerEntity customerEntity) {
		CustomerEntity savedCustomer = customerRepository.save(customerEntity);
		return savedCustomer;
	}

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
	@Override
	public CustomerEntity editCustomer(EditCustomerModel editCustomerModel) {
		
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

	/**
	 * Disable an already existing customer
	 * 
	 * @param email
	 * @return CustomerEntity
	 * 
	 * @author Ashish Sam T George
	 * 
	 */
	@Override
	public CustomerEntity disableCustomer(String email) throws InterruptedException, IOException {
		
		CustomerEntity customer = customerRepository.findByEmail(email);
//		customer.setStatus(StatusEnum.USER_DISABLED);
		customer.setEnableStatus(StatusEnum.USER_DISABLED);
		CustomerEntity disabledCustomer = customerRepository.save(customer);

		//TODO Call API of AuthNAuthZ microservice to disable the user
//		userLoginService.disableUser(email);
		
		HttpClient httpClient = HttpClient.newHttpClient();
		//TODO Is this the correct URI
		URI uri = URI.create("http://AUTH-SERVICE/disableUser?email=" + email);
		HttpRequest userRequest = HttpRequest.newBuilder()
									.uri(uri)
									.DELETE()
									.build();
		HttpResponse<String> response = httpClient.send(userRequest,
										HttpResponse.BodyHandlers.ofString());
		//TODO Check the response
		
		System.out.println("Response from authentication microservice: " + response.body());
		
//		return customer;
		return disabledCustomer;
	}

	/**
	 * Enable an already existing customer
	 * 
	 * @param email
	 * @return CustomerEntity
	 * 
	 * @see CustomerEntity
	 * 
	 * @author Ashish Sam T George
	 * 
	 */
	@Override
	public CustomerEntity enableCustomer(String email) throws InterruptedException, IOException {
		
		//TODO Consider who makes this call (customer or admin) and then do the needful
		
		CustomerEntity customer = customerRepository.findByEmail(email);
//		customer.setStatus(StatusEnum.ENABLED);
		customer.setEnableStatus(StatusEnum.ENABLED);
		CustomerEntity savedCustomer = customerRepository.save(customer);

		//TODO Call API of AuthNAuthZ microservice to disable the user
//		userLoginService.disableUser(email);
		
		HttpClient httpClient = HttpClient.newHttpClient();
		//TODO Is this the correct address
		URI uri = URI.create("http://AUTH-SERVICE/enableUser?email=" + email);
		HttpRequest userRequest = HttpRequest.newBuilder()
									.uri(uri)
									.method("PATCH", null)
									.build();
		HttpResponse<String> response = httpClient.send(userRequest, 
										HttpResponse.BodyHandlers.ofString());
		
		//TODO Check response

//		return customer;
		return savedCustomer;
	}

	@Override
	public CustomerEntity disableCustomerByAdmin(String email) throws IOException, InterruptedException {
		// TODO Call authentication microservice to disable the user by admin
		CustomerEntity customer = customerRepository.findByEmail(email);
		customer.setEnableStatus(StatusEnum.ADMIN_DISABLED);
		CustomerEntity disabledCustomer = customerRepository.save(customer);
		
		HttpClient httpClient = HttpClient.newHttpClient();
		URI uri = URI.create("http://auth-service/auth/user/byAdmin?email=" + email);
		HttpRequest userRequest = HttpRequest.newBuilder()
									.uri(uri)
									.DELETE()
									.build();
		HttpResponse<String> response = httpClient.send(userRequest,
										HttpResponse.BodyHandlers.ofString());
		System.out.println("Response from authentication microservice: " + response.body());
		
		return disabledCustomer;
		
	}

	@Override
	public CustomerEntity enableCustomerByAdmin(String email) throws IOException, InterruptedException {
		// TODO Call authentication microservice to enable the user by admin
		CustomerEntity customer = customerRepository.findByEmail(email);
		customer.setEnableStatus(StatusEnum.ENABLED);
		CustomerEntity enabledCustomer = customerRepository.save(customer);
		
		HttpClient httpClient = HttpClient.newHttpClient();
		URI uri = URI.create("http://auth-service/auth/user/byAdmin?email=" + email);
		HttpRequest userRequest = HttpRequest.newBuilder()
									.uri(uri)
									.method("PATCH", null)
									.build();
		HttpResponse<String> response = httpClient.send(userRequest,
										HttpResponse.BodyHandlers.ofString());
		System.out.println("Response from authentication microservice: " + response.body());
		
		return enabledCustomer;
		
	}

}
