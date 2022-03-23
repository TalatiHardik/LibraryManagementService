package com.lib.authorization.model;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


//@AllArgsConstructor
//@NoArgsConstructor
//@Data
public class AuthenticationRequest {
	
	@NotNull(message = "Cannot be null")
	@NotBlank(message = "Cannot be Blank")
	@NotEmpty(message = "Cannot be Empty" )
	private String userName;	
	@NotNull(message = "Cannot be null")
	@NotBlank(message = "Cannot be Blank")
	@NotEmpty(message = "Cannot be Empty" )
	private String password;
	
	public AuthenticationRequest(
			@NotNull(message = "Cannot be null") @NotBlank(message = "Cannot be Blank") @NotEmpty(message = "Cannot be Empty") String userName,
			@NotNull(message = "Cannot be null") @NotBlank(message = "Cannot be Blank") @NotEmpty(message = "Cannot be Empty") String password) {
		super();
		this.userName = userName;
		this.password = password;
	}

	public AuthenticationRequest() {
		super();
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "AuthenticationRequest [userName=" + userName + ", password=" + password + "]";
	}
	
	
	
	
	
	

}
