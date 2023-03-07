/**
 * 
 */
package com.pigihi.service;

import java.util.List;

import com.pigihi.entity.CustomerEntity;

/**
 * Interface for customer query service <br>
 * Should not change values
 * 
 * @author Ashish Sam T George
 *
 */
public interface QueryServiceInterface {

	CustomerEntity customerInfo(String email);

	List<CustomerEntity> findAllCustomers();

}
