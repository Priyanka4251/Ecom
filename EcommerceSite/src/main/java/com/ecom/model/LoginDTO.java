package com.ecom.model;

import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor

@Table(name= "LoginDTOs")
public class LoginDTO {
	
	private String username;
	private String password;
}
