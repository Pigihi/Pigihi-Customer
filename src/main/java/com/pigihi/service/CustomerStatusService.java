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
//		customer.setStatus(StatusEnum.USER_DISABLED);
		customer.setEnableStatus(StatusEnum.USER_DISABLED);
		CustomerEntity disabledCustomer = customerRepository.save(customer);

//		HttpClient httpClient = HttpClient.newHttpClient();
//		//TODO Is this the correct URI
//		URI uri = URI.create("http://AUTH-SERVICE/disableUser?email=" + email);
//		HttpRequest userRequest = HttpRequest.newBuilder()
//									.uri(uri)
//									.DELETE()
//									.build();
//		HttpResponse<String> response1 = httpClient.send(userRequest,
//										HttpResponse.BodyHandlers.ofString());
		//TODO Check the response
		
		String uri = authUri.concat(disableUserEndpoint);
		HttpResponse<String> response = deleteRequestSender.send(uri, disableUserQueryParam, 
																	email);
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
	public CustomerEntity enableCustomer(String email) throws InterruptedException, IOException {
		
		//TODO Consider who makes this call (customer or admin) and then do the needful
		
		CustomerEntity customer = customerRepository.findByEmail(email);
//		customer.setStatus(StatusEnum.ENABLED);
		customer.setEnableStatus(StatusEnum.ENABLED);
		CustomerEntity savedCustomer = customerRepository.save(customer);

//		HttpClient httpClient = HttpClient.newHttpClient();
//		//TODO Is this the correct address
//		URI uri = URI.create("http://AUTH-SERVICE/enableUser?email=" + email);
//		HttpRequest userRequest = HttpRequest.newBuilder()
//									.uri(uri)
//									.method("PATCH", null)
//									.build();
//		HttpResponse<String> response1 = httpClient.send(userRequest, 
//										HttpResponse.BodyHandlers.ofString());
		
		//TODO Check response

		String uri = authUri.concat(enableUserEndpoint);
		HttpResponse<String> response = patchRequestSender.send(uri, enableUserQueryParam, 
																email);
		System.out.println("Response obtained from authentication service: " + response.body());
//		return customer;
		return savedCustomer;
	}

}
