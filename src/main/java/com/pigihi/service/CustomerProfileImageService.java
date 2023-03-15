package com.pigihi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pigihi.entity.CustomerEntity;
import com.pigihi.repository.CustomerRepository;

@Service
public class CustomerProfileImageService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	public CustomerEntity changeProfileImage(String email, String imageUrl) {
		CustomerEntity customer = customerRepository.findByEmail(email);
		customer.setImageUrl(imageUrl);
		CustomerEntity changedCustomer = customerRepository.save(customer);
		return changedCustomer;
	}

}
