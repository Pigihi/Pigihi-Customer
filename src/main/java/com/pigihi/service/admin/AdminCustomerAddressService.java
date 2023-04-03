package com.pigihi.service.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pigihi.entity.CustomerEntity;
import com.pigihi.model.CustomerAddressModel;
import com.pigihi.service.CustomerAddressService;

@Service
public class AdminCustomerAddressService {
	
	@Autowired
	private CustomerAddressService customerAddressService;
	
	public CustomerEntity changeAddress(String email, CustomerAddressModel customerAddressModel) {
		CustomerEntity customer = customerAddressService.changeAddress(email, customerAddressModel);
		return customer;
	}

}
