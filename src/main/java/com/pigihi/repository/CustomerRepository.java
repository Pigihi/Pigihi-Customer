/**
 * 
 */
package com.pigihi.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.pigihi.entity.CustomerEntity;

/**
 * Repository class for Customer
 * 
 * @author Ashish Sam T George
 *
 */
public interface CustomerRepository extends MongoRepository<CustomerEntity, String> {

	@Query("{email: ?0}")
	CustomerEntity findByEmail(String email);
	
	@Query("{$or: [{'email': ?0}, {'mobile': ?1}]}")
	List<CustomerEntity> findByEmailAndMobile(String email, String mobile);
	
}
