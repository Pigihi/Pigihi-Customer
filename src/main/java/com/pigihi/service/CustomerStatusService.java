package com.pigihi.service;

import java.io.IOException;
import java.net.http.HttpResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.pigihi.entity.CustomerEntity;
import com.pigihi.entity.StatusEnum;
import com.pigihi.repository.CustomerRepository;

@Service
public class CustomerStatusService {
	
	@Autowired
	private DELETERequestSender deleteRequestSender;
	
	@Autowired
	private PATCHRequestSender patchRequestSender;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Value("${authService.uri}")
	private String authUri;
	
	@Value("${authService.endpoint.enableUserOwn.endpoint}")
	private String enableUserEndpoint;
	
	@Value("${authService.endpoint.enableUserOwn.queryParam}")
	private String enableUserQueryParam;

	@Value("${authService.endpoint.disableUserOwn.endpoint}")
	private String disableUserEndpoint;
	
	@Value("${authService.endpoint.disableUserOwn.queryParam}")
	private String disableUserQueryParam;
	
	/**
	 * Disable an already existing customer
	 * 
	 * @param email
	 * @return CustomerEntity
	 * 
	 * @author Ashish Sam T George
	 * 
	 */
	public CustomerEntity disableCustomer(String email) throws InterruptedException, IOException {
		CustomerEntity customer = customerRepository.findByEmail(email);
		if(customer.getEnableStatus() != StatusEnum.ADMIN_DISABLED) {
			customer.setEnableStatus(StatusEnum.USER_DISABLED);
			CustomerEntity disabledCustomer = customerRepository.save(customer);

			//TODO Check the response
			
			String uri = authUri.concat(disableUserEndpoint);
			HttpResponse<String> response = deleteRequestSender.send(uri, disableUserQueryParam, 
																		email, email);
			System.out.println("Response from authentication microservice: " + response.body());
			return disabledCustomer;
		}
		else {
			return null;
		}
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
	public CustomerEntity enableCustomer(String email) throws InterruptedException, IOException {
		CustomerEntity customer = customerRepository.findByEmail(email);
		if(customer.getEnableStatus() != StatusEnum.ADMIN_DISABLED) {
			customer.setEnableStatus(StatusEnum.ENABLED);
			CustomerEntity enabledCustomer = customerRepository.save(customer);

			//TODO Check response

			String uri = authUri.concat(enableUserEndpoint);
			HttpResponse<String> response = patchRequestSender.send(uri, enableUserQueryParam, 
																	email, email);
			System.out.println("Response obtained from authentication service: " + response.body());
			return enabledCustomer;
		}
		else {
			return null;
		}
	}

}
