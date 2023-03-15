package com.pigihi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerPasswordModel {
	
	private String oldPassword;
	private String newPassword;
	private String confirmNewPassword;

}
