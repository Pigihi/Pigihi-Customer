package com.pigihi.service.admin;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pigihi.entity.CustomerEntity;
import com.pigihi.library.dataConverter.service.DataConverter;
import com.pigihi.service.CustomerNameService;

@Service
public class AdminCustomerNameService {
	
	@Autowired
	private CustomerNameService customerNameService;
	
	@Autowired
	private DataConverter dataConverter;
	
	public CustomerEntity changeFullName(String email, String fullName) throws IOException, InterruptedException {
		CustomerEntity customer = customerNameService.changeFullName(email, fullName);
		return customer;
	}

}
