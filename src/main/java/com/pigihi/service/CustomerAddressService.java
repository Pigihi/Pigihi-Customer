package com.pigihi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pigihi.entity.CustomerEntity;
import com.pigihi.model.CustomerAddressModel;
import com.pigihi.repository.CustomerRepository;

@Service
public class CustomerAddressService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	public CustomerEntity changeAddress(String email, CustomerAddressModel customerAddressModel) {
		CustomerEntity customer = customerRepository.findByEmail(email);
		customer.setHouseName(customerAddressModel.getHouseName());
		customer.setStreetName(customerAddressModel.getStreetName());
		customer.setCityName(customerAddressModel.getCityName());
		customer.setPincode(customerAddressModel.getPincode());
		
		CustomerEntity changedCustomer = customerRepository.save(customer);
		return changedCustomer;
	}

}
