/**
 * 
 */
package com.pigihi.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.pigihi.entity.CustomerEntity;
import com.pigihi.entity.StatusEnum;
import com.pigihi.model.EditCustomerModel;
import com.pigihi.repository.CustomerRepository;

/**
 * @author Ashish Sam T George
 *
 */
public class CustomerService implements CustomerServiceInterface {
	
	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public CustomerEntity saveCustomer(CustomerEntity customerEntity) {
		CustomerEntity savedCustomer = customerRepository.save(customerEntity);
		return savedCustomer;
	}

	@Override
	public CustomerEntity editCustomer(EditCustomerModel editCustomerModel) {
		
		CustomerEntity customer = customerRepository.findByEmail(editCustomerModel.getEmail());
		customer.setMobile(editCustomerModel.getMobile());
		customer.setImageUrl(editCustomerModel.getImageUrl());
		customer.setHouseName(editCustomerModel.getHouseName());
		customer.setStreetName(editCustomerModel.getStreetName());
		customer.setCityName(editCustomerModel.getCityName());
		customer.setPincode(editCustomerModel.getPincode());
		
		customerRepository.save(customer);
		return customer;
		
	}

	@Override
	public CustomerEntity disableCustomer(String email) {
		
		CustomerEntity customer = customerRepository.findByEmail(email);
		customer.setStatus(StatusEnum.USER_DISABLED);
		customerRepository.save(customer);

		//TODO Call API of AuthNAuthZ microservice to disable the user
//		userLoginService.disableUser(email);

		return customer;
		
	}

	@Override
	public CustomerEntity enableCustomer(String email) {
		
		//TODO Consider who makes this call (customer or admin) and then do the needful
		
		CustomerEntity customer = customerRepository.findByEmail(email);
		customer.setStatus(StatusEnum.ENABLED);
		customerRepository.save(customer);

		//TODO Call API of AuthNAuthZ microservice to disable the user
//		userLoginService.disableUser(email);

		return customer;
	}

}
