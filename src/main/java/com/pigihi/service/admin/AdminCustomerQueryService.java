package com.pigihi.service.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pigihi.entity.CustomerEntity;
import com.pigihi.repository.CustomerRepository;

@Service
public class AdminCustomerQueryService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	public List<CustomerEntity> findAllCustomers() {
		List<CustomerEntity> customers = customerRepository.findAll();
		return customers;
	}

}
