/**
 * 
 */
package com.pigihi.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.pigihi.entity.CustomerEntity;

/**
 * @author Ashish Sam T George
 *
 */
public interface CustomerRepository extends MongoRepository<CustomerEntity, String> {

	@Query("{email: ?0}")
	CustomerEntity findByEmail(String email);
	
}
