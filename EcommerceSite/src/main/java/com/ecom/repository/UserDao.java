package com.ecom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecom.model.User;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {

	public User findByUserName(String username);
	
	public User findByContactAndEmailAndUserLoginId(long contact, String email,int id);


}
