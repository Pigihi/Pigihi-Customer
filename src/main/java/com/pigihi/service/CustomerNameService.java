package com.pigihi.service;

import java.io.IOException;
import java.net.http.HttpResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.pigihi.entity.CustomerEntity;
import com.pigihi.repository.CustomerRepository;

@Service
public class CustomerNameService {
	
	@Autowired
	private PUTRequestSender putRequestSender;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Value("${authService.uri}")
	private String authUri;
	
	@Value("${authService.endpoint.fullName.endpoint}")
	private String fullNameEndpoint;
	
	@Value("${authService.endpoint.fullName.queryParam}")
	private String fullNameQueryParam;
	
	public CustomerEntity changeFullName(String email, String fullName) throws InterruptedException, IOException {
		CustomerEntity customer = customerRepository.findByEmail(email);
		customer.setFullName(fullName);
		CustomerEntity changedCustomer = customerRepository.save(customer);
		
		//TODO Create API endpoint for changing full name in authentication service
		String uri = authUri.concat(fullNameEndpoint);
		HttpResponse<String> response = putRequestSender.send(uri, "email", email, fullNameQueryParam, 
																fullName, "");
		System.out.println(response.uri());
		System.out.println("Response obtained from authentication service: " + response.body());
		return changedCustomer;
	}

}
