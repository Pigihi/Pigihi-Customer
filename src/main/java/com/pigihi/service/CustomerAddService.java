/**
 * 
 */
package com.pigihi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pigihi.entity.CustomerEntity;
import com.pigihi.repository.CustomerRepository;

/**
 * @author Ashish Sam T George
 *
 */
@Service
public class CustomerAddService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	/**
	 * Save customer details
	 * 
	 * @param customerEntity
	 * @return CustomerEntity
	 * 
	 * @see CustomerEntity
	 * 
	 * @author Ashish Sam T George
	 * 
	 */
	public CustomerEntity saveCustomer(CustomerEntity customerEntity) {
		CustomerEntity savedCustomer = customerRepository.save(customerEntity);
		return savedCustomer;
	}

}
