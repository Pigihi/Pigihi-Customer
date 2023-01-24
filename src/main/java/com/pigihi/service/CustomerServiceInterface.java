/**
 * 
 */
package com.pigihi.service;

import com.pigihi.entity.CustomerEntity;
import com.pigihi.model.EditCustomerModel;

/**
 * @author Ashish Sam T George
 *
 */
public interface CustomerServiceInterface {

	CustomerEntity saveCustomer(CustomerEntity customerEntity);

	CustomerEntity editCustomer(EditCustomerModel editCustomerModel);

	CustomerEntity disableCustomer(String email);

	CustomerEntity enableCustomer(String email);

}
