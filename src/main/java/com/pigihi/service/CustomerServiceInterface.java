/**
 * 
 */
package com.pigihi.service;

import com.pigihi.entity.CustomerEntity;
import com.pigihi.model.EditCustomerModel;

/**
 * Interface for customer service <br>
 * Contains methods that can change values
 * 
 * @author Ashish Sam T George
 *
 */
public interface CustomerServiceInterface {

	CustomerEntity saveCustomer(CustomerEntity customerEntity);

	CustomerEntity editCustomer(EditCustomerModel editCustomerModel);

	CustomerEntity disableCustomer(String email);

	CustomerEntity enableCustomer(String email);

}
