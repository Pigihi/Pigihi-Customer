package com.pigihi.service.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pigihi.entity.CustomerEntity;
import com.pigihi.service.CustomerProfileImageService;

@Service
public class AdminCustomerProfileImageService {
	
	@Autowired
	private CustomerProfileImageService customerProfileImageService;
	
	public CustomerEntity changeProfileImage(String email, String imageUrl) {
		CustomerEntity customer = customerProfileImageService.changeProfileImage(email, imageUrl);
		return customer;
	}

}
