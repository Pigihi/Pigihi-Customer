/**
 * 
 */
package com.pigihi.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

/**
 * Entity class for customer
 * 
 * @author Ashish Sam T George
 *
 */
@Document(collection = "customer_collection")
@Data
public class CustomerEntity {
	
	@Id
	private String id;
	private String fullName;
	private String email;
	private String role;
	private String mobile;
	private String imageUrl;
//	private StatusEnum status = StatusEnum.ENABLED;
	private StatusEnum enableStatus = StatusEnum.ENABLED;
	private String houseName;
	private String streetName;
	private String cityName;
	private String pincode;
	
	private List<PrivilegeEnum> privileges;

}
