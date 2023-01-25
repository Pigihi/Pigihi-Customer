/**
 * 
 */
package com.pigihi.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.pigihi.entity.CustomerEntity;
import com.pigihi.repository.CustomerRepository;

/**
 * Implementation class for customer query service interface
 * 
 * @author Ashish Sam T George
 *
 */
public class CustomerQueryService implements QueryServiceInterface {
	
	@Autowired
	private CustomerRepository customerRepository;

	/**
	 * Get details of customer
	 * 
	 * @param email
	 * @return CustomerEntity
	 * 
	 * @see CustomerEntity
	 * 
	 * @author Ashish Sam T George
	 * 
	 */
	@Override
	public CustomerEntity customerInfo(String email) {
		CustomerEntity customer = customerRepository.findByEmail(email);
		return customer;
	}

}
