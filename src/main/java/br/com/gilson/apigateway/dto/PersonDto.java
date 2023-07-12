package br.com.gilson.apigateway.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class PersonDto {

	@Size(min = 2, max = 120, message = "size must be between 2 and 120")
	@NotBlank(message = "must not be blank")
	private String firstName;

	@Size(min = 2, max = 120, message = "size must be between 2 and 120")
	@NotBlank(message = "must not be blank")
	private String lastName;

	@NotBlank(message = "must not be blank")
	private String address;

	@Pattern(regexp = "^[MF]$", message = "must match 'M' or 'F'")
	@NotBlank(message = "must not be blank")
	private String gender;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

}
