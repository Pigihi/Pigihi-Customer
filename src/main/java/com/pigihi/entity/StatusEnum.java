/**
 * 
 */
package com.pigihi.entity;

/**
 * Enum containing the statuses a customer can have <br>
 * ADMIN_DISABLED - The customer is disabled by the admin. Only an admin can enable the customer back. <br>
 * USER_DISABLED - The customer is disabled by the customer itself. Can enable by re-verification of email and mobile. <br>
 * ENABLED - The customer is enabled after successful verification of email and mobile. <br>
 * DISABLED - The customer is created but not yet verified email or mobile. <br>
 * 
 * @author Ashish Sam T George
 *
 */
public enum StatusEnum {
	
	//TODO Define this enum like in AuthNAuthZ microservice
	
	ADMIN_DISABLED(760),
	USER_DISABLED(761),
	ENABLED(779),
	DISABLED(766);
	
	public int customerStatusCode;
	
	StatusEnum(int customerStatusCode){
		this.customerStatusCode = customerStatusCode;
	}

}
