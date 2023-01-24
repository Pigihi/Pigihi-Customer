/**
 * 
 */
package com.pigihi.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.pigihi.entity.CustomerEntity;
import com.pigihi.repository.CustomerRepository;

/**
 * @author Ashish Sam T George
 *
 */
public class CustomerQueryService implements QueryServiceInterface {
	
	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public CustomerEntity customerInfo(String email) {
		CustomerEntity customer = customerRepository.findByEmail(email);
		return customer;
	}

}
