/**
 * 
 */
package com.pigihi.service;

import com.pigihi.entity.CustomerEntity;

/**
 * @author Ashish Sam T George
 *
 */
public interface QueryServiceInterface {

	CustomerEntity customerInfo(String email);

}
