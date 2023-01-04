package com.ecom.service;

import com.ecom.exception.LoginException;
import com.ecom.model.LoginDTO;

public interface LoginService {
	
    public String login(LoginDTO dto) throws LoginException;
	
	public String logout(String key)throws LoginException;
}
