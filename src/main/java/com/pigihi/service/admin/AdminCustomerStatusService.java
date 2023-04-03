/**
 * 
 */
package com.pigihi.service.admin;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.pigihi.entity.CustomerEntity;
import com.pigihi.entity.StatusEnum;
import com.pigihi.library.dataConverter.service.DataConverter;
import com.pigihi.repository.CustomerRepository;
import com.pigihi.service.DELETERequestSender;
import com.pigihi.service.PATCHRequestSender;

/**
 * @author Ashish Sam T George
 *
 */
@Service
public class AdminCustomerStatusService {
	
	@Autowired
	private DELETERequestSender deleteRequestSender;
	
	@Autowired
	private PATCHRequestSender patchRequestSender;
	
	@Autowired
	private DataConverter dataConverter;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Value("${authService.uri}")
	private String authUri;
	
	@Value("${authService.endpoint.disableUser.endpoint}")
	private String disableUserEndpoint;
	
	@Value("${authService.endpoint.disableUser.queryParam}")
	private String disableUserQueryParam;
	
	@Value("${authService.endpoint.enableUser.endpoint}")
	private String enableUserEndpoint;
	
	@Value("${authService.endpoint.enableUser.queryParam}")
	private String enableUserQueryParam;

	public CustomerEntity disableCustomerByAdmin(String email) throws IOException, InterruptedException {

		CustomerEntity customer = customerRepository.findByEmail(email);
		customer.setEnableStatus(StatusEnum.ADMIN_DISABLED);
		CustomerEntity disabledCustomer = customerRepository.save(customer);
		String uri = authUri.concat(disableUserEndpoint);
		HttpResponse<String> response = deleteRequestSender.send(uri, disableUserQueryParam, 
																	email);
		System.out.println("Response from authentication microservice: " + response.body());
		return disabledCustomer;
		
	}
	
	public CustomerEntity enableCustomerByAdmin(String email) throws IOException, InterruptedException {

		CustomerEntity customer = customerRepository.findByEmail(email);
		System.out.println("Obtained Customer: " + customer);
		customer.setEnableStatus(StatusEnum.ENABLED);
		CustomerEntity enabledCustomer = customerRepository.save(customer);		
		String uri = authUri.concat(enableUserEndpoint);
		HttpResponse<String> response = patchRequestSender.send(uri, enableUserQueryParam, 
																email);
		System.out.println("Response from authentication microservice: " + response.body());
		return enabledCustomer;
		
	}

}
